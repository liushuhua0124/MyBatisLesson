package cn.et.lesson03.sql;

import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface FoodMapper {
	//��ѯ����
	public List queryFood(String foodname,String price);
	
	
	//ɾ������
	public void deleteFood(String foodid);
	
	//��ѯ����
	public List queyFoodAll();
}
