package com.jobportal.utility;

public class OtpEmailTemplate {

    public static String getOtpTemplate(String otp, String user) {
        return """
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8">
            <title>Your OTP - JobHook</title>
        </head>
        <body style="font-family: Arial, sans-serif; background-color: #f4f7fc; margin: 0; padding: 0;">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td align="center" style="padding: 40px 0;">
                        <table width="500" style="background: #ffffff; border-radius: 10px;
                        box-shadow: 0px 4px 12px rgba(0,0,0,0.1);" cellpadding="20">

                            <tr>
                                <td align="center" style="background: #004aad; color: #ffffff;
                                border-radius: 10px 10px 0 0;">
                                    <h2 style="margin: 0;">JobHook</h2>
                                    <p style="margin: 0; font-size: 14px;">Secure Verification</p>
                                </td>
                            </tr>

                            <tr>
                                <td style="color: #333333; font-size: 15px; line-height: 1.6;">
                                    <p>Hi,  
                                    """ + user + """
                                    ,</p>
                                    <p>Use the OTP below to verify your email:</p>

                                    <div style="text-align: center; margin: 20px 0;">
                                        <span style="font-size: 28px; font-weight: bold;
                                        color: #004aad; letter-spacing: 5px;">
                                            """ + otp + """
                                        </span>
                                    </div>

                                    <p>If you did not request this, please ignore this email.</p>
                                </td>
                            </tr>

                            <tr>
                                <td align="center" style="font-size: 13px; color: #666666;">
                                    <p style="margin: 0;">© 2025 JobHook Team</p>
                                </td>
                            </tr>

                        </table>
                    </td>
                </tr>
            </table>
        </body>
        </html>
        """;
    }
}
