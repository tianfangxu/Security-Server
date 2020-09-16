export default {
	//公共方法
	timeForMart : v => {
		if(!v){return ''}
		return v.substring(0,4)+"-"+v.substring(4,6)+"-"+v.substring(6,8)+" "+v.substring(8,10)+":"+v.substring(10,12)+":"+v.substring(12,14);
	},
	stringToJson : v => {
		return JSON.parse(v);
	},
	stringSubLenfth:v =>{
		return v.length < 30?v:v.substring(0,30)+".....";
	},
}
