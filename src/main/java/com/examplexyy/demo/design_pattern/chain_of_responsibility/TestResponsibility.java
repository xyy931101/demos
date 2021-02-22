package com.examplexyy.demo.design_pattern.chain_of_responsibility;

/**
 * @Author: Xiongyy
 * @Date: 2021/2/21 23:20
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class TestResponsibility {

    public static void main(String[] args) {

        PreparationList preparationList = new PreparationList();
        preparationList.setWashFace(true);
        preparationList.setWashHair(false);
        preparationList.setHaveBreakfast(true);

        Study study = new Study();

        StudyPrepareFilter washFaceFilter = new WashFaceFilter();
        StudyPrepareFilter washHairFilter = new WashHairFilter();
        StudyPrepareFilter haveBreakfastFilter = new HaveBreakfastFilter();

        FilterChain filterChain = new FilterChain(study);
        filterChain.addFilter(washFaceFilter);
        filterChain.addFilter(washHairFilter);
        filterChain.addFilter(haveBreakfastFilter);

        filterChain.before();

        filterChain.doFilter(preparationList, filterChain);
        study.study();
        filterChain.after();
    }
}
