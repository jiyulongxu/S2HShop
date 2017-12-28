package main.com.ssh.shop.dao.impl;


import main.com.ssh.shop.dao.GoodsDao;
import main.com.ssh.shop.entity.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("goodsDao")
public class GoodsDaoImpl extends BaseDaoImpl<Goods> implements GoodsDao {

    @Override
    public List<Goods> queryToGoods(String name, int page, int size) {
        String hql;
        name = name.trim();
        if (!name.equals("") && !name.equals(" ")) {
            hql = "from Goods as p where p.name like :name";
            //System.out.println("1");
            return getSession().createQuery(hql).
                    setString("name", "%" + name + "%").
                    setFirstResult((page - 1) * size).
                    setMaxResults(size).list();
        } else {
            hql = "from Goods";
            System.out.println("2");
            return getSession().createQuery(hql).
                    setFirstResult((page - 1) * size).
                    setMaxResults(size).list();
        }

    }

    @Override
    public Long getCount(String name) {
        name = name.trim();
        String hql;
        if (name != null || !name.equals("")) {
            hql = "select count(c) from Goods c where c.name like :name";
        } else {
            hql = "select count(c) from Goods c";
        }
        return (Long) getSession().createQuery(hql)
                .setString("name", "%" + name + "%")
                .uniqueResult(); //返回一条记录:总记录数
    }
}
