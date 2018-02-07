package com.sethu.androidtemplatecode.rxandroid.operators.mathematical_and_aggregate;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

//reduce operator apply a function to each item emitted by an Observable,
// sequentially, and emit the final value
public class ReduceOperator {
    public static Maybe<Integer> getReduceOperator(){
        return Observable.just(1,2,3,4,5,6)
          .reduce(new BiFunction<Integer, Integer, Integer>() {
                @Override
                public Integer apply(Integer integer, Integer integer2) throws Exception {
                    return integer + integer2;
                }
          });
    }
}
//place this code in onCreate method of RxAndroid2DemoActivity to watch the output
//     ReduceOperator.getReduceOperator()
//             .subscribeOn(Schedulers.newThread())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(new MaybeObserver<Integer>() {
//        @Override
//        public void onSubscribe(Disposable d) {
//
//        }
//
//        @Override
//        public void onSuccess(Integer integer) {
//            textView.append(" "+integer +"  reduce operator  Completed \n");
//        }
//
//        @Override
//        public void onError(Throwable e) {
//            textView.append(" error "+e);
//        }
//
//        @Override
//        public void onComplete() {
//            textView.append("  reduce operator  Completed");
//        }
//    });