package com.example.demo.service.impl;

import com.daou.BizSend;
import com.daou.entity.SendMsgEntity;
import com.example.demo.service.SMSService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import static com.example.demo.utils.DateUtils.getLocalNow;


@Service
@RequiredArgsConstructor
public class SMSServiceImpl implements SMSService {
	@Value("${sms.id}")
	final String id;

	@Value("${sms.pw}")
	final String pw;

	final MessageSource messageSource;

	/**
	 * 메시지 전송 테스트
	 */
	public void send(String mobile, String msg) {
		String title = messageSource.getMessage("MSG.002", null, LocaleContextHolder.getLocale());

		BizSend bs        = new BizSend();    // 메시지 전송 클래스
		SendMsgEntity sme = null;             // 메시지 클래스

		boolean msgEnryptOpt = false;		  // 수신번호, 메세지 내용을 암호화 하지 않을 경우
		//boolean msgEnryptOption = true;		  // 수신번호, 메세지 내용을 암호화 할 경우

		boolean fileEncryptOpt = false;		  //첨부 파일 암호화 전송
		//boolean fileEncryptOpt = true;	  //첨부 파일 암호화 전송 하지 않음

		boolean fileDeleteOpt = false;		  //첨부 파일 전송 후 삭제 하지 않음
		//boolean fileDeleteOpt = true;       //첨부 파일 전송 후 삭제

		/* Console 로그 설정 */
		// Console 에서 로그를 확인할 경우 설정
		bs.setLogEnabled(true);

		/* 서버 연결 시작 & 인증 */
		// ip   = biz.ppurio.com
		// port = 18100 (SSL로 연결, Default) / 5000 / 15001
		// 일반 포트 연결(5000 / 15001) 시 use_ssl 파라미터는 false로 설정
		// bs.doBegin("biz.ppurio.com", 18100, "user_id", "user_pw");
		// 또는
		// bs.doBegin("biz.ppurio.com", port, "user_id", "user_pw", use_ssl);
		bs.doBegin("biz.ppurio.com", 5000, id, pw, false);

		/* 전송할 파일 경로 설정 */
		// 전송할 파일이 있을 경우 설정
		// ex. FAX, PHONE, MMS 등
//		bs.setFilePath("./spool");

		/* 블랙리스트 파일 경로 설정 */
		// 블랙리스트를 사용할 경우 설정
//		bs.setBlkPath("./blk");

		/* PING-PONG */
		// 장시간 연결하여 메시지를 전송할 경우 연결이 끊어지지 않기 위해 실행
		// 이전 호출 시점으로부터 30초 이상 지난 경우에만 PING 을 전송하도록 함수내  정의되어 있음
		// 실행하지 않아도 메시지 전송에는 영향을 미치지 않으나 안전한 연결을 위해 주기적으로 실행하길 권장
//		boolean bool = bs.sendPing();
//

		/* 리포트 재요청 */
		// msgid 에 해당하는 메시지의 리포트가 시간이 지나도 오지 않는 경우 실행
//		bool = bs.reconfirmReport("");    // @param msgid    다우기술의 서버에서 정의한 message id
//

		/* 메시지 정의 */
		// 다음의 setter 를 사용하여 필요한 정보를 정의
		// ex. SMS 의 경우, MSG_TYPE, DEST_PHONE, SEND_PHONE, MSG_BODY 정보를 정의
		sme = new SendMsgEntity();
		sme.setCMID      ("patient" + getLocalNow("yyyyMMddHHmmssSSS"));    // 데이터 id
		sme.setMSG_TYPE  (5);    // 데이터 타입 (SMS 0 / WAP 1 / FAX 2 / PHONE 3 / SMS_INBOUND 4 / MMS 5)
		sme.setSEND_TIME ("");    // 발송 (예약) 시간 (Unix Time, 정의하지 않을 경우 즉시 발송)
		sme.setDEST_PHONE(mobile);    // 받는 사람 전화 번호
		sme.setDEST_NAME ("");    // 받는 사람 이름
		sme.setSEND_PHONE("0234462502");    // 보내는 사람 전화 번호
		sme.setSEND_NAME ("");    // 보내는 사람 이름
		sme.setSUBJECT   (title);    // (FAX/MMS) 제목, (SMS_INBOUND) 데이터 내용
		sme.setMSG_BODY  (msg);    // 데이터 내용
		sme.setWAP_URL   ("");    // (WAP) URL 주소
		sme.setCOVER_FLAG(0 );    // (FAX) 표지 발송 옵션
		sme.setSMS_FLAG  (0 );    // (PHONE) PHONE 실패 시 문자 전송 옵션
		sme.setREPLY_FLAG(0 );    // (PHONE) 응답 받기 선택
		sme.setRETRY_CNT (0 );    // (FAX/PHONE) 재시도 회수 (5~10분 간격: 최대 3회)
		sme.setFAX_FILE  ("");    // (PHONE/FAX/MMS) 파일 전송시 파일 이름
		sme.setVXML_FILE ("");    // (PHONE) 음성 시나리오 파일 이름

		/* 메시지 전송 */
		try {
			String msgid = "";
			/**
			 * @param  sme       SendMsgEntity
			 * @param  msgEncryptOpt	수신번호, 메세지 내용을 암호화 전송할지 여부(암호화 : true/비암호화 : false)
			 * @param  fileEncryptOpt   첨부파일 암호화 전송여부(암호화 : true/비암호화 : false)
			 * @param  fileDeleteOpt	첨부파일 전송 후 삭제 여부(파일 삭제 : trye/파일 삭제하지 않음 : false)
			 * @retrun String    다우기술의 서버에서 정의한 message id
			 */
			//msgid = bs.sendMsg(sme, msgEnryptOpt, fileEncryptOpt, fileDeleteOpt);
			msgid = bs.sendMsg(sme, msgEnryptOpt, fileEncryptOpt, fileDeleteOpt);

		} catch (Exception ex) {
			System.out.println("Failed to Send a Msg" +
					", " + ex.getMessage());
		}

		/* 서버 연결 종료 */
		bs.doEnd();
	}
}
