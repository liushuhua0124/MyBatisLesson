package cn.et.lesson02.xml;

import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface FoodMapper {
	//查询方法
	public List queryFood(String foodname,String price);
	
	public List queryFoodname(@Param("foodname") String foodname);
	
	//删除方法
	public void deleteFood(String foodid);
	
	//插入food对象
	public void saveFood(FoodAll food);
}
