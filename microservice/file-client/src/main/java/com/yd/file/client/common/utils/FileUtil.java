package com.yd.file.client.common.utils;

/*
 * 文件名：FileUtils.java 版权：Copyright by www.huawei.com 描述： 修改人：kokJuis 修改时间：2017年7月27日 跟踪单号： 修改单号：
 * 修改内容：
 */

public class FileUtil
{

    /**
     * Description: 获取文件后缀名
     *
     * @param fileName
     * @return
     * @see
     */
    public static String getExtensionName(String fileName)
    {
        String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
        return prefix;
    }

    /**
     * 根据path获取文件名
     *
     * @author kokJuis
     * @version 1.0
     * @date 2016-12-12
     * @param filename
     * @return
     */
    public static String getOriginalFilename(String filename)
    {
        if (filename == null) return "";
        int pos = filename.lastIndexOf("/");
        if (pos == -1) pos = filename.lastIndexOf("\\");
        if (pos != -1)
            return filename.substring(pos + 1);
        else
            return filename;
    }

}
