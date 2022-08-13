package com.examplexyy.demo.design_pattern.chain_of_responsibility;



/**
 * @Author: Xiongyy
 * @Date: 2021/2/21 23:17
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class WashFaceFilter implements StudyPrepareFilter {


    @Override
    public void before() {
        System.out.println("洗头发之前");
    }

    @Override
    public void doFilter(PreparationList preparationList, FilterChain filterChain) {
        System.out.println("洗完头发");

        filterChain.doFilter(preparationList, filterChain);
    }

    @Override
    public void after() {
        System.out.println("洗头发之后");
    }
}
