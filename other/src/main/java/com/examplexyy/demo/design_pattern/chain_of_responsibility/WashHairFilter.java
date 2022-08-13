package com.examplexyy.demo.design_pattern.chain_of_responsibility;

/**
 * @Author: Xiongyy
 * @Date: 2021/2/21 23:18
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class WashHairFilter implements StudyPrepareFilter {


    @Override
    public void before() {

    }

    @Override
    public void doFilter(PreparationList preparationList, FilterChain filterChain) {
        System.out.println("洗完脸");

        filterChain.doFilter(preparationList, filterChain);
    }

    @Override
    public void after() {

    }

}
