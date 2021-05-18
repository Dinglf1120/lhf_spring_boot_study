package com.lhf.springboot.springbootmybatis.service;

import java.util.List;

public interface BaseService<T> {

	/**
	 * 插入一条记录
	 * @param paramT 实体类泛型
	 * @throws Exception
	 */
	public Integer save(T paramT);

	/**
	 * 批量插入
	 * @param list
	 * @return
	 */
	public Integer saveList(List list);

	/**
	 * 修改一条记录
	 * @param paramT 实体类泛型
	 */
	public Integer update(T paramT);

	/**
	 * 删除一条记录
	 * @param pk  主键ID
	 */
	public Integer delete(Long pk);

	/**
	 * 按ID查询一条记录
	 * @param pk 主键ID
	 * @return 记录实体类
	 */
	public T queryById(Object pk);
	
	/**
	 * 多条件查询单条
	 * @param paramT 实体类参数
	 * @return 实体类集合
	 */
	public T queryOne(T paramT);

	/**
	 * 多条件查询全部记录
	 * @param paramT 实体类参数
	 * @return 实体类集合
	 */
	public List<T> queryList(T paramT);

	/**
	 * 多条件分页查询
	 * @param paramT 实体类参数
	 * @param page   页码
	 * @param pageSize 页面大小
	 * @return 实体类集合
	 */
	public List<T> queryListPaged(T paramT, Integer page, Integer pageSize);
	
	/**
	 * 多条件查询全部记总数
	 * @param paramT 实体类参数
	 * @return 
	 */
	public Integer queryCount(T paramT);


}
