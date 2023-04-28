/*****
 * RSA加密解密工具类
 * **/
import { JSEncrypt } from 'jsencrypt' // 导入
// 公钥
const publicKey = 'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnxG0QijjYoVzXmgpnBtKDWRcMHLI9WD3giLJmGY2EqRj60S3E33B8SLL0LJkT36Yqs3Nlcy/1MDkdfd+LmE3S2q8DxG6GF2nCjs3WP83ovWmm9smhGk1yB40O1QXbunllEo8D3UtN9Xb32yOjsQr3nDhKTpU6aEBCGjaI4PTFTcSgjRyQbpuvTOfTZjNidyzJNJGd+EFHAgPqypzO/czOaD2FjaO5XZ6t6/8WFRSM4GtWVsGYI2WWklsGShR+a1XbVWIli75s3QbOk/R2Xq1oYpnHAxw9EXQYFP7keyXc7I9yW/RmRtMN21u8JsVovONaJsdLzzpHTestS9/D57+6QIDAQAB'
// 私钥
const privateKey = ''

// 加密
export function encrypt (txt: string) {
  const encryptor = new JSEncrypt()
  encryptor.setPublicKey(publicKey) // 设置公钥
  return encryptor.encrypt(txt) // 对需要加密的数据进行加密
}

// 解密
export function decrypt (txt: string) {
  const encryptor = new JSEncrypt()
  encryptor.setPrivateKey(privateKey)
  return encryptor.decrypt(txt)
}

