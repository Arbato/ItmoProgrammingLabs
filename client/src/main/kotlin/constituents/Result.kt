package constituents

class Result() {
    private  var message: String = ""

    private  var text: String = ""


    enum class ResultStatus {
        SUCCESS, FAIL
    }
    private  var status: ResultStatus = ResultStatus.FAIL


    fun getMessage(): String{
        return this.message
    }
    fun setMessage(s:String){
        this.message = s
    }

    fun setText(s: String){
        text = s
    }
    fun getText(): String{
        return text
    }
    fun setResult(s: ResultStatus){
        status = s
    }
    fun getResult(): ResultStatus{
        return status
    }

    fun success(){
        status = ResultStatus.SUCCESS
    }

    fun fail(){
        status = ResultStatus.FAIL
    }
    fun setMsg(msg: String){
        message = msg
    }
    fun setTxt(msg: String){
        text = msg
    }
    override fun toString(): String {
        if (status == ResultStatus.FAIL){
            return "\u001B[31m"+ message +"\u001B[0m"
        } else {
            return "\u001B[32m"+ message +"\u001B[0m"
        }
    }
}