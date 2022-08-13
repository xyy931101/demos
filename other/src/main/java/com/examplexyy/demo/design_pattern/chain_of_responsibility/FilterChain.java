package com.examplexyy.demo.design_pattern.chain_of_responsibility;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Xiongyy
 * @Date: 2021/2/21 23:26
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class FilterChain implements StudyPrepareFilter{

    private int pos = 0;

    private Study study;

    private List<StudyPrepareFilter> studyPrepareFilterList;

    public FilterChain(Study study) {
        this.study = study;
    }

    public void addFilter(StudyPrepareFilter studyPrepareFilter) {
        if (studyPrepareFilterList == null) {
            studyPrepareFilterList = new ArrayList<StudyPrepareFilter>();
        }

        studyPrepareFilterList.add(studyPrepareFilter);
    }

    @Override
    public void before() {

    }

    @Override
    public void doFilter(PreparationList thingList, FilterChain filterChain) {
        // 所有过滤器执行完毕
        if(pos < studyPrepareFilterList.size()){
            StudyPrepareFilter filter = studyPrepareFilterList.get(pos++);
            filter.before();
            filter.doFilter(thingList, filterChain);
            filter.after();
        }
    }

    @Override
    public void after() {

    }
}
