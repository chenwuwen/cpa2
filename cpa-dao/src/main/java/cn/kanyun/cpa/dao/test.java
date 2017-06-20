package cn.kanyun.cpa.dao;

import cn.kanyun.cpa.model.system.CpaRole;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Set;

/**
 * Created by Administrator on 2017/6/20.
 */
public class test {


    public static void main(String[] args) {
        Integer userId = 2;
         exeute(userId);

    }
    public static void exeute(Integer userId){
        Session session = HibernateSessionFactory.getSession();
        String hql = "select o from UserRole o where userId = :userId";
        Query query = session.createQuery(hql);
        query.setParameter("userId",userId);
       Set<CpaRole> sets  = (Set<CpaRole>) query.list();
       for (CpaRole role:sets){
           System.out.println(role.getRoleName());
       }
    }
}
