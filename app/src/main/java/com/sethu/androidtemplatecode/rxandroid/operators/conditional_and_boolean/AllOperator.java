package com.sethu.androidtemplatecode.rxandroid.operators.conditional_and_boolean;
import com.sethu.androidtemplatecode.rxandroid.operators.creating.JustOperator;
import io.reactivex.Single;
import io.reactivex.functions.Predicate;

//all operator determine whether all items emitted by an Observable meet some criteria

public class AllOperator {

    public static Single allOperatorFalseCondition(){
        return JustOperator.getJustObservable()
                .all(new Predicate() {
                    @Override
                    public boolean test(Object o) throws Exception {
                        return o.toString().matches("[a-zA-Z2-9]+");
                    }
                });
    }

    public static Single allOperatorTrueCondition(){
        return JustOperator.getJustObservable()
                .all(new Predicate() {
                    @Override
                    public boolean test(Object o) throws Exception {
                        return o.toString().matches("[a-zA-Z0-9]+");
                    }
                });
    }
//place this code in onCreate method of RxAndroid2DemoActivity to watch the output
//    Single<Object> observable = AllOperator.allOperatorFalseCondition();
//        observable.observeOn(Schedulers.newThread())
//                .subscribeOn(AndroidSchedulers.mainThread())
//            .subscribe(new SingleObserver<Object>() {
//        @Override
//        public void onSubscribe(Disposable d) {
//
//        }
//
//        @Override
//        public void onSuccess(Object o) {
//            textView.append("  "+o.toString());
//        }
//
//        @Override
//        public void onError(Throwable e) {
//            textView.append("  "+e);
//        }
//    });

}

