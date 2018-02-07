package com.sethu.androidtemplatecode.rxandroid.operators.conditional_and_boolean;
import com.sethu.androidtemplatecode.rxandroid.operators.creating.FromCallableOperator;
import com.sethu.androidtemplatecode.rxandroid.operators.creating.JustOperator;
import io.reactivex.Observable;

//amb operator:- given two or more source Observables, emit all of the items from
// only the first of these Observables to emit an item

public class AmbOperator {
    public static Observable<Object> getAmbOperator() {
        return FromCallableOperator.fromCallableOperator()
                .ambWith(JustOperator.getJustObservable());
    }
}

//place this code in onCreate method of RxAndroid2DemoActivity to watch the output
//    AmbOperator.getAmbOperator()
//            .observeOn(Schedulers.newThread())
//            .subscribeOn(AndroidSchedulers.mainThread())
//            .subscribe(new Observer<Object>(){
//                @Override
//                public void onSubscribe(Disposable d){ }
//
//                @Override
//                public void onNext(Object o){
//                        textView.append("  "+o);
//                }
//
//                @Override
//                public void onError(Throwable e){
//                        textView.append(" "+e);
//                }
//
//                @Override
//                public void onComplete(){ }
//            });