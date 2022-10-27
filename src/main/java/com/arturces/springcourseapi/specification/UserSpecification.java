package com.arturces.springcourseapi.specification;

import com.arturces.springcourseapi.domain.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSpecification {

    public static Specification<User> search(String text){
        return new Specification<User>() {
            private static final long serialVersionUID = 8980447740301031040L;

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if(text == null || text.trim().length() <= 0)
                    return null;

                String likeTerm = "%" + text + "%";

                Predicate predicate = cb.or(cb.like(root.get("name"), likeTerm),
                        cb.or(cb.like(root.get("email"),likeTerm),
                                cb.like(root.get("role").as(String.class), likeTerm)));

                return predicate;
            }
        };
    }
}

