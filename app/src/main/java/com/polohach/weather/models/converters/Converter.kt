package com.polohach.weather.models.converters

import io.reactivex.FlowableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import org.json.JSONArray

interface Converter<IN, OUT> {

    /**
     * Convert IN to OUT
     *
     * @param inObject object ot convertIterableToList
     * @return converted object
     */
    fun convertInToOut(inObject: IN): OUT

    /**
     * Convert OUT to IN
     *
     * @param outObject object ot convertIterableToList
     * @return converted object
     */
    fun convertOutToIn(outObject: OUT): IN?

    /**
     * Convert List of IN to List of OUT
     *
     * @param inObjects [List] of objects to convertIterableToList
     * @return [List] of converted objects
     */
    fun convertListInToOut(inObjects: List<IN>?): List<OUT>?

    /**
     * Convert List of OUT to List of IN
     *
     * @param outObjects [List] of objects to convertIterableToList
     * @return [List] of converted objects
     */
    fun convertListOutToIn(outObjects: List<OUT>?): List<IN>?

    /**
     * Returns Transformer for converting Observable with single IN to OUT
     *
     * @return Instance of [ObservableTransformer]
     */
    fun observableINtoOUT(): ObservableTransformer<IN?, OUT?>

    /**
     * Returns Transformer for converting Observable with single OUT to IN
     *
     * @return Instance of [ObservableTransformer]
     */
    fun observableOUTtoIN(): ObservableTransformer<OUT?, IN?>

    /**
     * Returns Transformer for converting Observable with List of IN to List of OUT
     *
     * @return Instance of [ObservableTransformer]
     */
    fun listObservableINtoOUT(): ObservableTransformer<List<IN>?, List<OUT>?>

    /**
     * Returns Transformer for converting Observable with List of OUT to List of IN
     *
     * @return Instance of [ObservableTransformer]
     */
    fun listObservableOUTtoIN(): ObservableTransformer<List<OUT>?, List<IN>?>

    /**
     * Returns Transformer for converting Observable with single IN to OUT
     *
     * @return Instance of [FlowableTransformer]
     */
    fun flowINtoOUT(): FlowableTransformer<IN?, OUT?>

    /**
     * Returns Transformer for converting Observable with single OUT to IN
     *
     * @return Instance of [FlowableTransformer]
     */
    fun flowOUTtoIN(): FlowableTransformer<OUT?, IN?>

    /**
     * Returns Transformer for converting Observable with List of IN to List of OUT
     *
     * @return Instance of [FlowableTransformer]
     */
    fun listFlowINtoOUT(): FlowableTransformer<List<IN>?, List<OUT>?>

    /**
     * Returns Transformer for converting Observable with List of OUT to List of IN
     *
     * @return Instance of [FlowableTransformer]
     */
    fun listFlowOUTtoIN(): FlowableTransformer<List<OUT>?, List<IN>?>

    /**
     * Returns Transformer for converting Observable with single OUT to IN
     *
     * @return Instance of [SingleTransformer]
     */
    fun singleOUTtoIN(): SingleTransformer<OUT, IN>

    /**
     * Returns Transformer for converting Observable with single IN to OUT
     *
     * @return Instance of [SingleTransformer]
     */
    fun singleINtoOUT(): SingleTransformer<IN?, OUT>

    /**
     * Returns Transformer for converting Observable with List of OUT to List of IN
     *
     * @return Instance of [SingleTransformer]
     */
    fun listSingleOUTtoIN(): SingleTransformer<List<OUT>, List<IN>>

    /**
     * Returns Transformer for converting Observable with List of IN to List of OUT
     *
     * @return Instance of [SingleTransformer]
     */
    fun listSingleINtoOUT(): SingleTransformer<List<IN>, List<OUT>>

    /**
     * Returns Transformer for converting JSONArray to List of OUT
     *
     * @return Instance of [SingleTransformer]
     */
    fun jsonArraySingleINtoOUT(): SingleTransformer<JSONArray?, List<OUT>?>
}
