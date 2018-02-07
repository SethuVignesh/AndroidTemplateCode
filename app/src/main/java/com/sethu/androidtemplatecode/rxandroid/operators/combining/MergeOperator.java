package com.sethu.androidtemplatecode.rxandroid.operators.combining;
import com.sethu.androidtemplatecode.rxandroid.operators.creating.JustOperator;
import io.reactivex.Observable;

//merge operator combine multiple Observables into one by merging their emissions

public class MergeOperator {
    public static Observable<String> mergeOperator(){
       Observable<String> stringObservable =  JustOperator.getJustObservable();
       Observable<String> stringObservable1 = JustOperator.getJustObservable();
       return Observable.merge(stringObservable,stringObservable1);
    }
//place this code in onCreate method of RxAndroid2DemoActivity to watch the output
//     MergeOperator.mergeOperator().observeOn(Schedulers.newThread())
//            .subscribeOn(AndroidSchedulers.mainThread())
//            .subscribe(new Observer<String>() {
//        @Override
//        public void onSubscribe(Disposable d) {
//
//        }
//
//        @Override
//        public void onNext(String s) {
//            textView.append("  "+s);
//        }
//
//        @Override
//        public void onError(Throwable e) {
//            textView.append(" error in merge operator "+e);
//        }
//
//        @Override
//        public void onComplete() {
//            textView.append("  merge operator complete \n");
//        }
//    });
}