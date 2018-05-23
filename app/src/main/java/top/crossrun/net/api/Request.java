package top.crossrun.net.api;

import top.crossrun.net.interf.RecycleAble;

public abstract class Request implements RecycleAble{
    public abstract void http();
}
