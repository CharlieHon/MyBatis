package com.charlie.mapper;

import com.charlie.entity.Monster;
import org.apache.ibatis.annotations.*;

import java.util.List;

// MonsterAnnotation：使用注解的方式来配置接口
public interface MonsterAnnotation {
    // 添加monster
    /*
    1. 使用注解方式配置接口方法addMonster
        <insert id="addMonster" parameterType="Monster" useGeneratedKeys="true" keyProperty="id">
            INSERT INTO `monster`(`age`, `birthday`, `email`, `gender`, `name`, `salary`) VALUES
                (#{age}, #{birthday}, #{email}, #{gender}, #{name}, #{salary});
        </insert>
    2. useGeneratedKeys = true 返回自增长的值
    3. keyProperty = "id" 自增值对应的对象的属性
    4. keyColumn = "id" 自增值对应表的字段
    5. 如果keyColumn和ketProperty名字一致，则可以省略keyColumn，建议都写上
     */
    @Insert("INSERT INTO `monster`(`age`, `birthday`, `email`, `gender`, `name`, `salary`) VALUES " +
            "(#{age}, #{birthday}, #{email}, #{gender}, #{name}, #{salary})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public void addMonster(Monster monster);

    // 根据id删除一个monster
    /* xml文件中的配置
    <delete id="delMonster" parameterType="Integer">
        DELETE FROM `monster` WHERE id=#{id}
    </delete>
     */
    @Delete("DELETE FROM `monster` WHERE id=#{id}")
    public void delMonster(Integer id);

    // 修改Monster
    /* xml文件配置
        <update id="updateMonster" parameterType="Monster">
            UPDATE `monster` SET `age`=#{age}, `birthday`=#{birthday}, `email`=#{email}, `gender`=#{gender}, `name`=#{name},
                    `salary`=#{salary} WHERE `id`=#{id};
        </update>
     */
    @Update("UPDATE `monster` SET `age`=#{age}, `birthday`=#{birthday}, `email`=#{email}, `gender`=#{gender}, `name`=#{name}, " +
            "`salary`=#{salary} WHERE `id`=#{id}")
    public void updateMonster(Monster monster);

    // 查询-根据id
    /* xml文件配置
    <select id="getMonsterById" parameterType="Integer" resultType="Monster">
        SELECT * FROM `monster` WHERE `id`=#{id};
    </select>
     */
    @Select("SELECT * FROM `monster` WHERE `id`=#{id}")
    public Monster getMonsterById(Integer id);

    // 查询所有的Monster
    /* xml文件配置
        <select id="findAllMonsters" resultType="Monster">
            SELECT * FROM `monster`;
        </select>
     */
    @Select("SELECT * FROM `monster`")
    public List<Monster> findAllMonsters();
}
