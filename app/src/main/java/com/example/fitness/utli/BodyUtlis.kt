package com.example.fitness.utli

//身体质量测量类
object BodyUtlis {


    //BMI=体重（kg）/身高²（m）
    public fun countBMI(height:Int,weight:Int) = weight/Math.pow(height.toDouble()/100,2.0)

    public fun judgeByBMI(BMI:Double):String{
        var result :String =""
          if (BMI<= 18.4)
              result = "偏瘦"
            else
              if(BMI> 18.4 && BMI<=23.9)
                  result ="正常"
            else
                  if (BMI>23.9&&BMI<=27.9)
                      result = "过重"
            else
                      if (BMI>=28)
                          result = "肥胖"
        return result
    }
}