package dao;

import domain.CountUserOrderAdd;

public interface CountUserOrderAddMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CountUserOrderAdd record);

    int insertSelective(CountUserOrderAdd record);

    CountUserOrderAdd selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CountUserOrderAdd record);

    int updateByPrimaryKey(CountUserOrderAdd record);
}