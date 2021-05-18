package com.lhf.springboot.springbootmybatis.service.impl;



import com.lhf.springboot.springbootmybatis.mapper.BaseMapper;
import com.lhf.springboot.springbootmybatis.service.BaseService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Transactional
public class BaseServiceImpl<T> implements BaseService<T> {
	
	@Autowired
	BaseMapper<T> baseMapper;
	
	@Autowired
	public void setIBaseDao(BaseMapper<T> baseMapper) {
		this.baseMapper = baseMapper;
	}

	@Override
	public Integer save(T paramT) {
		return baseMapper.insert(paramT);
	}

	@Override
	public Integer saveList(List list){
		return baseMapper.insertList(list);
	}



	@Override
	public Integer update(T paramT) {
		return baseMapper.updateByPrimaryKeySelective(paramT);
	}

	@Override
	public Integer delete(Long pk) {
		return baseMapper.deleteByPrimaryKey(pk);
	}

	@Override
	public T queryById(Object pk) {
		return baseMapper.selectByPrimaryKey(pk);
	}

	@Override
	public T queryOne(T paramT) {
		List<T> result = baseMapper.select(paramT);
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}

	@Override
	public List<T> queryList(T paramT) {
		return baseMapper.select(paramT);
	}

	@Override
	public List<T> queryListPaged(T paramT, Integer page, Integer pageSize) {
		@SuppressWarnings("unchecked")
		Class < T >  entityClass  = 
		(Class < T > ) 
		((ParameterizedType) getClass().getGenericSuperclass())
		.getActualTypeArguments()[ 0 ];
		// 开始分页
//        PageHelper.startPage(page, pageSize);
		Example example = new Example(entityClass);
//		List<T> userList =baseMapper.select(paramT);// baseMapper.selectByExample(example);
		RowBounds rb = new RowBounds(page, pageSize);
		List<T> userList = baseMapper.selectByExampleAndRowBounds(example, rb);
		return userList;
	}
	
	@Override
	public Integer queryCount(T paramT) {
		return baseMapper.selectCount(paramT);
	}

}
