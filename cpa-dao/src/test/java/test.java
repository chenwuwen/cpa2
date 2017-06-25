import ch.qos.logback.core.net.SyslogOutputStream;
import cn.kanyun.cpa.dao.HibernateSessionFactory;
import cn.kanyun.cpa.model.system.CpaRole;
import cn.kanyun.cpa.model.system.UserRole;
import cn.kanyun.cpa.model.user.CpaUser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/6/20.
 */
public class test {


    public static void main(String[] args) {
        Integer userId = 1;
         exeute(userId);

    }
    public static void exeute(Integer userId){
        Session session = HibernateSessionFactory.getSession();
        String hql = " from UserRole o where o.userId = :userId";
        Query query = session.createQuery(hql);
        query.setParameter("userId",userId);
        List<UserRole> list = (List<UserRole>) query.list();
        System.out.print(list);
    }

    @Test
    public static void exeute2(){
        Session session = HibernateSessionFactory.getSession();
        String hql = " from CpaUser o where o.id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",1);
        List<CpaUser> list =  query.list();
        System.out.print(list);
    }
}
