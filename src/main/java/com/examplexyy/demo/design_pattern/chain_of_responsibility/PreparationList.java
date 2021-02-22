package com.examplexyy.demo.design_pattern.chain_of_responsibility;

/**
 * @Author: Xiongyy
 * @Date: 2021/2/21 23:21
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class PreparationList {

    /**
     * 是否洗脸
     */
    private boolean washFace;

    /**
     * 是否洗头
     */
    private boolean washHair;

    /**
     * 是否吃早餐
     */
    private boolean haveBreakfast;

    public boolean isWashFace() {
        return washFace;
    }

    public void setWashFace(boolean washFace) {
        this.washFace = washFace;
    }

    public boolean isWashHair() {
        return washHair;
    }

    public void setWashHair(boolean washHair) {
        this.washHair = washHair;
    }

    public boolean isHaveBreakfast() {
        return haveBreakfast;
    }

    public void setHaveBreakfast(boolean haveBreakfast) {
        this.haveBreakfast = haveBreakfast;
    }

    @Override
    public String toString() {
        return "ThingList [washFace=" + washFace + ", washHair=" + washHair + ", haveBreakfast=" + haveBreakfast + "]";
    }

}
