package com.sethu.androidtemplatecode.rxandroid.operators.combining;
import com.sethu.androidtemplatecode.rxandroid.operators.creating.FromCallableOperator;
import com.sethu.androidtemplatecode.rxandroid.operators.creating.JustOperator;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

//zip operator combine the emissions of multiple Observables together via a specified function
// and emit single items for each combination based on the results of this function

public class ZipOperator {
    public static Observable getZipOperator() {
    Observable observable = JustOperator.getJustObservable();
    Observable observable1 = FromCallableOperator.fromCallableOperator();
    return Observable.zip(observable,observable1, new BiFunction() {
        @Override
        public String apply(Object o, Object o2) throws Exception {
            return  checkForNullDataAndReturn(o,o2);
        }
    });
    }
    // this function is used to modify data emitted by observable
    //this function can be modified according to your use
    private static String checkForNullDataAndReturn(Object o, Object o2) {
        if(o == null){
            return o2.toString();
        }else if(o2 == null){
            return o.toString();
        }
        return o.toString()+ "  " + o2.toString();
    }
}
//place this code in onCreate method of RxAndroid2DemoActivity to watch the output
//    MapOperator.mapOperator(textView);
//    Observable<String> observable = ZipOperator.getZipOperator();
//        observable.observeOn(Schedulers.newThread())
//                .subscribeOn(AndroidSchedulers.mainThread())
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
//            textView.append("  zip operator complete \n");
//        }
//    });