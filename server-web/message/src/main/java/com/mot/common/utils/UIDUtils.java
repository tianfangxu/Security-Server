package com.mot.common.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class UIDUtils {

    private static String localIp = getLocalIp();

    /**
     * 当前系统ip+时间+用户code 组成的唯一标识
     * @return
     */
    public static String getSessionId(){
        int work = Integer.parseInt(getLocalIp().split("\\.")[3]);
        Snowflake instance = Snowflake.getInstance(work % 32, work / 32);
        long id = instance.nextId();
        int code = getLocalIp().hashCode();
        return code+"$"+id;
    }


    public static String getLocalIp(){
        if (localIp != null){
            return localIp;
        }
        InetAddress ip = null;
        String osName = System.getProperty("os.name");
        try {
            ip = osName.startsWith("Linux")?getUnixLocalIp():osName.startsWith("Windows")?InetAddress.getLocalHost():null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return (localIp=ip.getHostAddress());
    }

    /**
     *
     * 可能多多个ip地址只获取一个ip地址
     * 获取Linux 本地IP地址
     * @return
     * @throws SocketException
     */
    private static InetAddress getUnixLocalIp() throws SocketException {
        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        while(netInterfaces.hasMoreElements()){
            NetworkInterface ni= (NetworkInterface)netInterfaces.nextElement();
            ip=(InetAddress) ni.getInetAddresses().nextElement();
            if( !ip.isSiteLocalAddress()
                    && !ip.isLoopbackAddress()
                    && ip.getHostAddress().indexOf(":")==-1){
                return ip;
            }
            else{
                ip=null;
            }
        }
        return null;
    }
}
