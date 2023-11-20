/**
 * Copyright © 2016-2018 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ifyou.skypivot.sample.infrastructure.persistence.dao;

import com.datastax.driver.core.utils.UUIDs;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListenableFuture;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.ifyou.skypivot.sample.infrastructure.persistence.entity.BaseEntityInterface;
import com.ifyou.skypivot.sample.infrastructure.util.DaoUtil;
import com.ifyou.skypivot.sample.infrastructure.util.UUIDConverter;
import com.ifyou.skypivot.sample.infrastructure.util.PageOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Executors;


/**
 *  @Author wxf
 */
@Slf4j
public abstract class JpaAbstractDao<E extends BaseEntityInterface<D>, D>   implements Dao<D> {

	protected ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

	@PreDestroy
	void onDestroy() {
		service.shutdown();
	}


    protected abstract Class<E> getEntityClass();

    protected abstract CrudRepository<E, String> getCrudRepository();

    protected void setSearchText(E entity) {}

    @PersistenceContext
    protected EntityManager enttyMngr;

    protected Integer getCode(Boolean bNext, String sequenceName) {
		Integer rValue = null;
		String SQL = null;
		Object obj = null;

		sequenceName = sequenceName.toLowerCase();
		if(bNext) {
			SQL = getSQL("nextval", "", sequenceName);
		}
		else {
			SQL = getSQL("currval", "", sequenceName);
		}

		obj = this.execSQL(SQL, true);

		if(null != obj) {
			rValue = Integer.parseInt(String.valueOf(obj));
		}
		return rValue;
	}

    protected String getSQL(String actionName, String last, String sequenceName) {
		return String.format("select %s('%s'%s)", actionName, sequenceName, last);
	}

    /**
	 * 批量新建
	 * @param list
	 * @return
	 */
    @Transactional
    public List<D> saveNewAll(List<D> list){
		List<D> rList = new ArrayList<>();

		if(null != list) {
			List<E> entitys = new ArrayList<>();
			Boolean bClear = false;
			Integer iCount = 0;

			try {
				E entity = null;
				for(D d : list) {
					try {
						entity = this.domainToEntity(d);
						entity.setId(UUIDs.timeBased());
						entitys.add(entity);
						this.enttyMngr.persist(entity);
						bClear = true;
						iCount ++;
					} catch (Exception e) {
						// TODO: handle exception
					}
					Thread.sleep(1);
				}

				this.enttyMngr.flush();
				this.enttyMngr.clear();
				bClear = false;
			} catch (Exception e) {
				if(bClear) {
					this.enttyMngr.flush();
					this.enttyMngr.clear();
				}
				log.warn("saveNewAll(List<D> list) error:{}{}", System.lineSeparator(), e);
			}
			rList = DaoUtil.convertDataList(entitys);
		}

		return rList;
	}
    /**
     * 检查code是否存在
     * @param tableName 表名
     * @param codeFieldName code的数据库字段名
     * @param code code值
     * @param id 记录的id，用于验证更新
     * @param andAfterSQL，其他的And后面的SQL
     * @return
     */
    protected Boolean checkCodeExist(String tableName, String codeFieldName, Integer code, UUID id, String andAfterSQL) {
		Boolean bReturn = false;
		String SQL = String.format("SELECT * FROM %s WHERE %s=%d", tableName, codeFieldName, code);

		if(null != id) {
			String sTempSQL = String.format("%s AND id<>'%s'", SQL, uUIDToString(id));

			SQL = sTempSQL;
		}

		if(null != andAfterSQL) {

			String sTempSQL = String.format("%s AND '%s'", SQL, andAfterSQL);

			SQL = sTempSQL;
		}
		if(null != this.execSQL(SQL, true)) {
			bReturn = true;
		}
		return bReturn;
	}

    protected Object execSQL(String SQL, Boolean bSingleResult) {
		Object rObject = null;

		if(null != SQL && !SQL.isEmpty()) {

			Query query = enttyMngr.createNativeQuery(SQL);

			if(null != query) {
				try {
					if(bSingleResult) {
						rObject = query.getSingleResult();
					}
					else {
						rObject = query.getResultList();
					}
				} catch (Exception e) {
					//log.info(e.getMessage());
				}
			}
		}
		return rObject;
	}
    protected void updateCodeSquncCurrentValue(Integer code, String sequenceName) {
		/*
		 * 这种方式是否能避免多进程多线程访问冲突问题？触发器方式能否避免？
		 * 另外，数据库初始化的时候怎么办？原来的sequence开始值可能已经很大了，手动恢复初始值？
		 */

		sequenceName = sequenceName.toLowerCase();
        Integer currentValue = this.getCode(false, sequenceName);

		if(null == currentValue || currentValue < code) {
			String sLast = String.format(",%d,true", code);
			String SQL = this.getSQL("setval", sLast, sequenceName);

			log.info("SQL = " + SQL);
			log.info(String.format("select setval('%s',%d,true)", sequenceName, code));
			this.execSQL(SQL, true);
		}
	}

    @Override
    @Transactional
    public D save(D domain) {
        E entity = this.domainToEntity(domain);
        setSearchText(entity);
        log.debug("Saving entity {}", entity);
        if (entity.getId() == null) {
            entity.setId(UUIDs.timeBased());
        }
        entity = getCrudRepository().save(entity);
        return DaoUtil.getData(entity);
    }

	/**
	 * 生成UUID的string
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 15; i++) {
			log.info(UUIDConverter.fromTimeUUID(UUIDs.timeBased()));
		}

	}

    @Transactional
    public E domainToEntity(D domain) {
    	E entity;
        try {
			Constructor<E> constructor = getEntityClass().getConstructor(domain.getClass());
//			log.info(constructor);
			entity = constructor.newInstance(domain);
        } catch (Exception e) {
        	log.warn("", e);
            log.warn("Can't create entity for domain object {}", domain, e);
            throw new IllegalArgumentException("Can't create entity for domain object {" + domain + "}", e);
        }

        return entity;
    }

    @Override
    public D findById(UUID key) {
        log.debug("Get entity by key {}", key);
        Optional<E> optional = getCrudRepository().findById(UUIDConverter.fromTimeUUID(key));
        return optional.isPresent()? DaoUtil.getData(optional.get()) : null;
    }

    @Override
    public ListenableFuture<D> findByIdAsync(UUID key) {
        log.debug("Get entity by key async {}", key);
        return service.submit(() -> {
        	Optional<E> optional = getCrudRepository().findById(UUIDConverter.fromTimeUUID(key));
        	return optional.isPresent()? DaoUtil.getData(optional.get()) : null;
        });
    }

    @Override
    @Transactional
    public boolean removeById(UUID id) {
        String key = UUIDConverter.fromTimeUUID(id);
        getCrudRepository().deleteById(key);
        log.debug("Remove request: {}", key);
        return getCrudRepository().findById(key).isPresent() != true;
    }

    @Override
    public List<D> find() {
        List<E> entities = Lists.newArrayList(getCrudRepository().findAll());
        return entities.isEmpty()? new ArrayList<D>() : DaoUtil.convertDataList(entities);
    }

    /**
     * 根据排序和排序字段返回排序对象
     * @param sort DESC降序或ASC升序，默认为ASC
     * @param sFieldName 排序字段名，默认为id
     * @return Sort实例
     */
    public static Sort getSortByStringAndSortFieldName(String sort, String sFieldName) {
		Sort sortNew=null;

		if(null == sFieldName || sFieldName.isEmpty()) {
			sFieldName = "id";
		}
		ArrayList<Sort.Order> orders = new ArrayList<>();

		if(null == sort || sort.isEmpty()) {
			Sort.Order asc = Sort.Order.asc(sFieldName);
			orders.clear();
			orders.add(asc);
			sortNew =  Sort.by(orders);
		} else {
			if(sort.equals(Direction.ASC.toString())) {
				Sort.Order asc = Sort.Order.asc(sFieldName);
				orders.clear();
				orders.add(asc);
				sortNew= Sort.by(orders);
			}else {
				Sort.Order desc = Sort.Order.desc(sFieldName);
				orders.clear();
				orders.add(desc);
				sortNew= Sort.by(orders);
			}
		}
    	return sortNew;
    }


    public static String uUIDToString(UUID timeUUID){
    	String sID = null;

    	if(null != timeUUID) {
    		sID = UUIDConverter.fromTimeUUID(timeUUID);
    	}
        return sID;
    }

    public static UUID stringToUuid(String src){
    	UUID uuid = null;

    	if(null != src && !src.isEmpty()) {
    		uuid = UUIDConverter.fromString(src);
    	}
        return uuid;
    }
    /**
     * 根据页码、页面大小和排序规则返回Pageable实例
     * @param pageIndex 页码，默认为0
     * @param pageSize 页面大小，默认为10
     * @param sort 排序规则
     * @return Pageable实例
     */
    public static Pageable getPageable(Integer pageIndex, Integer pageSize, Sort sort) {

		Pageable pageable = null;

		if(null == pageIndex || pageIndex < 0) {
			pageIndex = 0;
		}
		if(null == pageSize || pageSize <= 0) {
			pageSize = 10;
		}
		pageable = PageRequest.of(pageIndex, pageSize, sort);
		return pageable;
    }

    /**
     * 根据参数返回Pageable实例
     * @param criteria 参数
     * @return Pageable实例
     */
    public static Pageable getPageable(PageOrder criteria) {
    	String sort = criteria.getSort();
		Integer pageIndex = criteria.getPageIndex();
		Integer pageSize = criteria.getPageSize();
		Sort sortNew = getSortByStringAndSortFieldName(sort, criteria.getSortName());

		return getPageable(pageIndex,pageSize,sortNew);
    }


	@Transactional
	public boolean removeAll() {
		getCrudRepository().deleteAll();
		log.debug("Remove all");
		return true;
	}

}
