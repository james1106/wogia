package com.magic.wogia.util;

public class FiledConstant {
	
	/**进水压力(MPa)*/
	public static final String  INLET_PRESSURE= "w1" ;
	/**设定压力*/
	public static final String  SET_PRESSURE= "w2" ;
	/**出水压力(MPa)*/
	public static final String  OUT_PRESSURE= "w3" ;
	/**1#变频器频率(HZ)*/
	public static final String  FREQUENCY_CONVERTER_ONE= "w4" ;
	/**瞬时流量(t/h)*/
	public static final String  FLOWRATE = "w5" ;
	/**累计流量（t）*/
	public static final String  CUMULATIVE_FLOWS = "w6" ;
	/**累计用电量（KWh）*/
	public static final String  COUNT_LECTRICITY= "w7" ;
	/**1#变频器电流（A）*/
	public static final String   FREQUENCY_CURRENT_ONE= "w8" ;
	/**吨水耗电量（KWh/t）*/
	public static final String   POWER_CONSUMPTION_OF_WATER= "w9" ;
	/**1#水箱液位(m³)*/
	public static final String   TANK_LEVEL_ONE= "w10" ;
	/**2#水箱液位(m³)*/
	public static final String  TANK_LEVEL_TWO= "w11" ;
	/**3#水箱液位(m³)*/
	public static final String  TANK_LEVEL_THREE= "w12" ;
	/**2#变频器频率(HZ)*/
	public static final String  FREQUENCY_CONVERTER_TWO= "w13" ;
	/**3#变频器频率(HZ)*/
	public static final String  FREQUENCY_CONVERTER_THREE= "w14" ;
	/**4#变频器频率(HZ)*/
	public static final String  FREQUENCY_CONVERTER_FOUR= "w15" ;
	/**5#变频器频率(HZ)*/
	public static final String  FREQUENCY_CONVERTER_FIVE= "w16" ;
	/**6#变频器频率(HZ)*/
	public static final String  FREQUENCY_CONVERTER_SIX= "w17" ;
	/**2#变频器电流(A)*/
	public static final String  FREQUENCY_CURRENT_TWO= "w18" ;
	/**3#变频器电流(A)*/
	public static final String  FREQUENCY_CURRENT_THREE= "w19" ;
	/**4#变频器电流(A)*/
	public static final String  FREQUENCY_CURRENT_FOUR= "w20" ;
	/**5#变频器电流(A)*/
	public static final String  FREQUENCY_CURRENT_FIVE= "w21" ;
	/**6#变频器电流(A)*/
	public static final String  FREQUENCY_CURRENT_SIX= "w22" ;
	/**水质-PH值*/
	public static final String  PH= "w23" ;
	/**水质-浊度(ntu)*/
	public static final String  TURBIDITY= "w24" ;
	/**水质-余氯（mg/L）*/
	public static final String  RESIDUAL_OXYGEN= "w25" ;
	/**水质-ORP(Mv)*/
	public static final String  ORP= "w26" ;
	/**水质-溶解氧（mg/l)*/
	public static final String  DISSOLVED_OXYGEN= "w27" ;
	/**水质-色度*/
	public static final String  CHROMA= "w28" ;
	/**水质-电导率（s/cm）*/
	public static final String  CONDUCTIVITY= "w29" ;
	/**1#泵变频运行信号 0-停机 1工频 2-变频 3故障 4检修 5-现场手动*/
	public static final String  PUMP_SIGNAL_ONE= "w30" ;
	/**2#泵变频运行信号 0-停机 1工频 2-变频 3故障 4检修 5-现场手动*/
	public static final String  PUMP_SIGNAL_TWO= "w31" ;
	/**3#泵变频运行信号 0-停机 1工频 2-变频 3故障 4检修 5-现场手动*/
	public static final String  PUMP_SIGNAL_THREE= "w32" ;
	/**4#泵变频运行信号 0-停机 1工频 2-变频 3故障 4检修 5-现场手动*/
	public static final String  PUMP_SIGNAL_FOUR= "w33" ;
	/**5#泵变频运行信号 0-停机 1工频 2-变频 3故障 4检修 5-现场手动*/
	public static final String  PUMP_SIGNAL_FIVE= "w34" ;
	/**6#泵变频运行信号 0-停机 1工频 2-变频 3故障 4检修 5-现场手动*/
	public static final String  PUMP_SIGNAL_SIX= "w35" ;
	
	/**1#泵温度（℃）*/
	public static final String PUMP_TEMPERATURE_ONE = "w36";
	/**2#泵温度（℃）*/
	public static final String PUMP_TEMPERATURE_TWO = "w37";
	/**3#泵温度（℃）*/
	public static final String PUMP_TEMPERATURE_THREE = "w38";
	/**4#泵温度（℃）*/
	public static final String PUMP_TEMPERATURE_FOUR = "w39";
	/**5#泵温度（℃）*/
	public static final String PUMP_TEMPERATURE_FIVE = "w40";
	/**6#泵温度（℃）*/
	public static final String PUMP_TEMPERATURE_SIX = "w41";
	
	/**1#泵变运行时间（H）*/
	public static final String PUMP_RUN_TEIME_ONE = "w42";
	/**2#泵变运行时间（H）*/
	public static final String PUMP_RUN_TEIME_TWO = "w43";
	/**3#泵变运行时间（H）*/
	public static final String PUMP_RUN_TEIME_THREE = "w44";
	/**4#泵变运行时间（H）*/
	public static final String PUMP_RUN_TEIME_FOUR = "w45";
	/**5#泵变运行时间（H）*/
	public static final String PUMP_RUN_TEIME_FIVE = "w46";
	/**6#泵变运行时间（H）*/
	public static final String PUMP_RUN_TEIME_SIX = "w47";
	
	/**泵房-噪音（dB）*/
	public static final String PUMP_HOUSE_NOISE = "w48";
	/**泵房-湿度（%RH）*/
	public static final String PUMP_HOUSE_HUMIDITY = "w49";
	/**泵房-温度（℃）*/
	public static final String PUMP_HOUSE_TEMPERATURE = "w50";
	/**泵房-火险 0-正常1-故障*/
	public static final String PUMP_HOUSE_FIRE = "w51";
	/**泵房-门禁 0-正常1-故障*/
	public static final String PUMP_HOUSE_ENTRANCE_GUARD = "w52";
	/**泵房-排水系统故障 0-正常1-故障*/
	public static final String PUMP_HOUSE_DRAINAGE_SYSTEM = "w53";
	/**泵房-水箱缺水 0-正常2-高液位3-溢流*/
	public static final String PUMP_HOUSE_WATER_BOX = "w54";
	/**总电量*/
	public static final String TOTAL_ELECTRICITY = "w55";
	
	
	/**出口压力超高 0-正常1-故障*/
	public static final String OUTLET_PRESSURE_SUPER_HIGH= "w60" ;
	/**稳流罐 0-正常1-故障*/
	public static final String  INLET_PRESSURE_LOW= "w61";
	/**自动 1-自动*/
	public static final String  AUTO= "w62";
	/**手动 1-自动*/
	public static final String  MANUAL= "w63";
	/**1#变频器故障 0-正常1-故障*/
	public static final String  FREQUENCY_STATUS= "w64";
	/**2#变频器故障 0-正常1-故障*/
	public static final String  FREQUENCY_STATUS_TWO= "w65";
	/**3#变频器故障  0-正常1-故障*/
	public static final String  FREQUENCY_STATUS_THREE= "w66";
	/**4#变频器故障  0-正常1-故障*/
	public static final String  FREQUENCY_STATUS_FOUR= "w67";
	/**5#变频器故障  0-正常1-故障*/
	public static final String  FREQUENCY_STATUS_FIVE= "w68";
	/**6#变频器故障  0-正常1-故障*/
	public static final String  FREQUENCY_STATUS_SIX= "w69";
	

}
