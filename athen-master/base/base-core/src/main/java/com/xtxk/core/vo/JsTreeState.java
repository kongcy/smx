package com.xtxk.core.vo;
import java.io.Serializable;

/**
 * opened    : boolean  // is the node open
 * disabled  : boolean  // is the node disabled
 * selected  : boolean  // is the node selected
 *
 * js tree 状态
 */
public class JsTreeState implements Serializable{
    private static final long serialVersionUID=-561197024090900858L;
    private Boolean opened=false;
    private Boolean disabled=false;
    private Boolean selected=false;
    public JsTreeState(){
    }
    public Boolean getDisabled(){
        return disabled;
    }
    public void setDisabled(Boolean disabled){
        this.disabled=disabled;
    }
    public Boolean getSelected(){
        return selected;
    }
    public void setSelected(Boolean selected){
        this.selected=selected;
    }
    public Boolean getOpened(){
        return opened;
    }
    public void setOpened(Boolean opened){
        this.opened=opened;
    }
}