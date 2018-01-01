package cn.et.lesson02.annotion;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

public interface FoodMapper {
	//查询方法
	@Select("select * from food where foodname = #{foodname} and price = #{price}")
	public List<Map> queryFood(@Param("foodname") String foodname,@Param("price") String price);
	
	//查询方法,根据名字去查
	@Select("select * from food where foodname like '%${foodname}%'")
	public List<Food> queryFoodAll(@Param("foodname") String foodname);
	
	//删除方法
	@Delete("delete from food where foodid = #{0}")	
	public void deleteFood(String foodid);
	
	
	
	@SelectKey(before=true,keyProperty="foodid",resultType=int.class,statement="select FOOD_SEC.nextval from dual")
	@Insert("insert into food values(#{foodid},#{foodname},#{price})")
	public void saveFood(Food food);
}
