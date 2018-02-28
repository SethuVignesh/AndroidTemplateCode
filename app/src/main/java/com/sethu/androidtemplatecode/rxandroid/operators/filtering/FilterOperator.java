package com.sethu.androidtemplatecode.rxandroid.operators.filtering;
import com.sethu.androidtemplatecode.rxandroid.operators.creating.FromCallableOperator;
import io.reactivex.Observable;
import io.reactivex.functions.Predicate;

//filter operator:- emit only those items from an Observable that pass a predicate test

public class FilterOperator {
    public static Observable getFilterOperator(){
        return FromCallableOperator.fromCallableOperator().filter(new Predicate<Object>() {
            @Override
            public boolean test(Object o) throws Exception {
                if(o.toString().matches("joice|abc")){
                    return true;
                }
                return false;
            }
        });
    }
}

//place this code in onCreate method of RxAndroid2DemoActivity to watch the output
//    Observable<Integer> observable = FilterOperator.getFilterOperator();
//        observable.observeOn(Schedulers.newThread())
//                .subscribeOn(AndroidSchedulers.mainThread())
//            .subscribe(new Observer<Object>() {
//        @Override
//        public void onSubscribe(Disposable d) {
//
//        }
//
//        @Override
//        public void onNext(Object s) {
//            textView.append("  "+ s );
//        }
//
//        @Override
//        public void onError(Throwable e) {
//            textView.append(" error in merge operator "+e);
//        }
//
//        @Override
//        public void onComplete() {
//            textView.append("  Filter operator completed \n");
//        }
//    });