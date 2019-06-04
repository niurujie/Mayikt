import com.mayikt.entity.UserEntity;
import com.mayikt.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: TestMyBatis
 * @description: 每特教育独创第五期互联网架构课程
 * @date 2019/6/420:46
 */
public class TestMyBatis {
    // 1.需要引入mybatisjar包
    // 2.配置核心mybatis文件 数据源、mapper接口映射
    // 3.需要sqlmapper文件 sql数据 orm
    // 4.通过mybatis操作../
    // 疑问：你们在mybatis整合springboot之后需要在每个mapper 需要加入注入spring容器注解 这是为什么呢？
    // 疑问：Mapper如何调用的呢
    public static void main(String[] args) {

        try {
            // 基本mybatis环境
            // 1.定义mybatis_config文件地址
            String resources = "mybatis_config.xml";
            // 2.获取InputStreamReaderIo流
            Reader reader = Resources.getResourceAsReader(resources);
            // 3.获取SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            // 4.获取Session
            SqlSession sqlSession = sqlSessionFactory.openSession();
            // 5.操作Mapper接口
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            UserEntity user = mapper.getUser(2);
            System.out.println(user.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 看源码的时候 看不懂 不知道如何下手。最重要的一点学会画图。 如何加源码呢？
     */
}
