package com.sethu.androidtemplatecode.rxandroid.operators.conditional_and_boolean;
import com.sethu.androidtemplatecode.rxandroid.operators.creating.FromCallableOperator;
import io.reactivex.Single;

//contains operator determine whether an Observable emits a particular item or not

public class ContainsOperator{
    public static Single<Boolean> getContainsOperator(){
        return FromCallableOperator.fromCallableOperator()
                .contains("joice");
    }
}
//place this code in onCreate method of RxAndroid2DemoActivity to watch the output
//    Single<Boolean> observable = ContainsOperator.getContainsOperator();
//        observable.observeOn(Schedulers.newThread())
//                .subscribeOn(AndroidSchedulers.mainThread())
//            .subscribe(new SingleObserver<Boolean>() {
//        @Override
//        public void onSubscribe(Disposable d) {
//
//        }
//        @Override
//        public void onSuccess(Boolean o) {
//            textView.append(FromCallableOperator.getList()+"  contains joice " + o.toString() + "\n");
//        }
//        @Override
//        public void onError(Throwable e) {
//            textView.append("  "+e);
//        }
//    });