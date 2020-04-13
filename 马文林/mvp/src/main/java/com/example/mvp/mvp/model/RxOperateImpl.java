package com.example.mvp.mvp.model;

import com.example.mvp.app.App;
import com.example.mvp.callback.IDataCallBack;
import com.example.mvp.di.component.DaggerRxOperateComponent;
import com.example.mvp.mvp.model.api.ApiService;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava和OkHttp关联的封装类
 */
public class RxOperateImpl {
    @Inject
    ApiService mApiService;

    public RxOperateImpl() {
        //将apiService注入到RxOperateImpl里面
        DaggerRxOperateComponent.builder().
                appComponent(App.daggerAppComponent()).build().
                inject(this);
    }

    /**
     * @param url          get请求的URL地址
     * @param dataCallBack 结果回调(实际上是接口回调)
     * @param <T>          接口回调获取的值
     */
    public <T> void requestData(String url, IDataCallBack<T> dataCallBack) {
        RxSchedulersOperator.retryWhenOperator(mApiService.requestData(url)).
                subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(Object value) {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
