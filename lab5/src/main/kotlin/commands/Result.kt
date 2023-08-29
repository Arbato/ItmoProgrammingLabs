package commands

class Result {
    private lateinit var message: String
    private enum class ResultStatus{
        SUCCESS, FAIL
    }
    private lateinit var status: ResultStatus

    fun success(){
        status = ResultStatus.SUCCESS
    }

    fun fail(){
        status = ResultStatus.FAIL
    }
    fun setMsg(msg: String){
        message = msg
    }

    override fun toString(): String {
        if (status == ResultStatus.FAIL){
            return "\u001B[31m"+ message +"\u001B[0m"
        } else {
            return "\u001B[32m"+ message +"\u001B[0m"
        }
    }

}