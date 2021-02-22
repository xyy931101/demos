package com.examplexyy.demo.design_pattern.chain_of_responsibility;

/**
 * @Author: Xiongyy
 * @Date: 2021/2/21 23:26
 * .............................................
 * 佛祖保佑             永无BUG
 */
public interface StudyPrepareFilter {

    public void before();

    /**
     * 责任链的doFilter
     * @param preparationList
     * @param filterChain
     */
    public void doFilter(PreparationList preparationList, FilterChain filterChain);


    public void after();


}
