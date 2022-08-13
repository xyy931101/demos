package com.examplexyy.demo.design_pattern.chain_of_responsibility;

/**
 * @Author: Xiongyy
 * @Date: 2021/2/21 23:19
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class HaveBreakfastFilter implements StudyPrepareFilter {

    @Override
    public void before() {
        System.out.println("吃早饭之前==========");
    }

    @Override
    public void doFilter(PreparationList preparationList, FilterChain filterChain) {

        System.out.println("吃完早饭");

        filterChain.doFilter(preparationList, filterChain);
    }

    @Override
    public void after() {
        System.out.println("吃早饭之后==========");
    }

}
