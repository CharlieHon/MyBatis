package com.charlie.mapper;

import com.charlie.entity.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserMapperAnnotation {
    /**
     * 注解形式的配置就是对应xml文件配置的改写
     *     <resultMap id="UserResultMap" type="User">
     *         <id property="id" column="id"/>
     *         <result property="name" column="name"/>
     *         <collection property="pets" column="id" ofType="Pet"
     *                     select="com.charlie.mapper.PetMapper.getPetByUserId"/>
     *     </resultMap>
     *     <select id="getUserById" resultMap="UserResultMap" parameterType="Integer">
     *         select * from `mybatis_user` where `id`=#{id};
     *     </select>
     */
    @Select("select * from `mybatis_user` where `id`=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            // 这里需要注意，pets属性user对应的是集合
            @Result(property = "pets", column = "id",
                    many = @Many(select = "com.charlie.mapper.PetMapperAnnotation.getPetByUserId"))
    })
    public User getUserById(Integer id);
}
