package ua.com.juja.core.week8.lab40ByteFilter;

/**
 ���������� ����������� ����� �� ��������� ����������
 public static void filter(InputStream src, OutputStream dst, int bufferSize, byte filterCriteria) throws IOException
 �� ���� �������� ����� ������.
 ���� ������ - ���������� � �������� ����� (stream) ��� ����� �� ����������� ������, ������� ��������� filterCriteria.
 ��� ����������� ��� ��������� � �������� ������ ������������ ������, ������ �������� �������� �������� ���������� bufferSize.
 ������ � ��������� ����� ����� ������ �������� ����.
 ��������:
 �������� ����� ������
 1 0 0 2 34 5 6 7 78 8 9 0 2 3 5 6 7 7
 filterCriteria = 0
 bufferSize = 4
 ��������� ����� ����� ���
 1 2 34 5 6 7 78 8 9 2 3 5 6 7 7.
 ������ ������� ������� read/write

 read(b[4])  - ��������� 4 ����� � ������ � �������� ��������
 write(b[4],0,1) - ���������� 1-� ���� � ��������� �����
 write(b[4],3,1) - �������� 2 ��������� ����� ���������� 4-� ���� � ��������� �����
 read(b[4]) -  ��������� 4 ����� � ������ � �������� ��������
 write(b[4],0,4) - ��� 4 ����� ���������� � ��������� �����
 read(b[4]) - ��������� 4 ����� � ������ � �������� ��������
 write(b[4],0,3)- ���������� ������ 3 ����� � ��������� ����� � �.�.
 read(b[4])
 write(b[4],0,4)
 read(b[4])
 write(b[4],0,2)
 read(b[4])
 */

import java.io.*;
import java.util.Arrays;

public class ByteFilter {
    public static void main(String[] args) throws IOException {
        final int BUFF_LEN = 4;
        final byte FILTER_CRITERIA=0;

        final byte[] DATA = {
                1, 0, 0, 2, 34, 5, 6, 7, 78, 8, 9, 0, 2, 3, 5, 6, 7, 7
        };

        byte[] expectedOutData = {
                1, 2, 34, 5, 6, 7, 78, 8, 9, 2, 3, 5, 6, 7, 7
        };

        //call
        byte[] actualOutData = null;

        ByteArrayInputStream dataInputStream = new ByteArrayInputStream(DATA);

        ByteArrayOutputStream dataOutputStream = new ByteArrayOutputStream();
        ByteFilter.filter(dataInputStream, dataOutputStream, BUFF_LEN,FILTER_CRITERIA);
        actualOutData = dataOutputStream.toByteArray();

        //check
        if (Arrays.equals(actualOutData, expectedOutData))
            System.out.print("OK");
        else
            throw new AssertionError("DATA equals " + Arrays.toString(DATA) + " and expected data is " + Arrays.toString(expectedOutData) + " but found " + Arrays.toString(actualOutData));
    }
    public static void filter(InputStream src, OutputStream dst, int bufferSize, byte filterCriteria) throws IOException {
        /*BODY*/
        byte[] buffer = new byte[bufferSize];
        int number = 0;

        while ( (number = src.read(buffer)) != -1) {
            for ( int i = 0, len = 0, off = 0; i < number; i++ ) {
                if ( filterCriteria == buffer[i] ) {
                    if ( len > 0 ) {
                        dst.write(buffer, off, len);
                        off += 1;
                        len = 0;
                    }

                    off += 1;
                } else {
                    len += 1;

                    if ( i + 1 == number ) {
                        dst.write(buffer, off, len);

                        len = 0;
                    }
                }
            }
        }

    }
}
