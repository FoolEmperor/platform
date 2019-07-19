<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybaits.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="pers.lance.platform.dao.mapper.CodeTemplateMapper">

    <select id="listCodeTemplateVO" parameterType="pers.lance.platform.bean.query.CodeTemplateQuery" resultType="pers.lance.platform.bean.vo.CodeTemplateVO">
        select t.*
        from code_template t
        where 1=1
        <if test="name != null and name !=''">
            and t.name like concat('%',#{name},'%')
        </if>
    </select>

    <select id="getCodeTemplateVO" resultType="pers.lance.platform.bean.vo.CodeTemplateVO">
        select t.*
        from code_template t
        where t.id = #{id}
    </select>

</mapper>
