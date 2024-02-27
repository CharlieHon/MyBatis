package com.charlie.mapper;

import com.charlie.entity.Pet;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PetMapperAnnotation {
    /**
     * 通过User的id来获取pet对象，可能有多个，因此使用List接收
     *     public List<Pet> getPetByUserId(Integer userId);-->
     *     <resultMap id="PetResultMap" type="Pet">
     *         <id property="id" column="id"/>
     *         <result property="nickname" column="nickname"/>
     *         <association property="user" column="user_id" select="com.charlie.mapper.UserMapper.getUserById"/>
     *     </resultMap>
     *     <select id="getPetByUserId" parameterType="Integer" resultMap="PetResultMap">
     *         select * from `mybatis_pet` where `user_id`=#{userId}
     *     </select>
     * 1. 这里的id就是给@Results 起个名字，方便复用
     * 2. @ResultMap("PetResultMap") 引用上面定义的 @Results
     */
    @Select("select * from `mybatis_pet` where `user_id`=#{id}")
    @Results(id = "PetResultMap", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "user", column = "user_id", one = @One(select = "com.charlie.mapper.UserMapperAnnotation.getUserById"))
    })
    public List<Pet> getPetByUserId(Integer userId);

    /**
     * 通过pet的id返回Pet对象
     *     <resultMap id="PetResultMap2" type="Pet">
     *         <id property="id" column="id"/>
     *         <result property="nickname" column="nickname"/>
     *         <association property="user" column="user_id" select="com.charlie.mapper.UserMapper.getUserById"/>
     *     </resultMap>
     *     <select id="getPetById" parameterType="Integer" resultMap="PetResultMap2">
     *         select * from `mybatis_pet` where `id`=#{id}
     *     </select>
     */
    @Select("select * from `mybatis_pet` where `id`=#{id}")
    @ResultMap("PetResultMap")
    public Pet getPetById(Integer id);
}
