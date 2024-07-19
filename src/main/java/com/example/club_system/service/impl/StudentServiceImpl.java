package com.example.club_system.service.impl;

import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.club_system.constants.ResMessage;
import com.example.club_system.entity.Club;
import com.example.club_system.entity.Student;
import com.example.club_system.entity.TeacherDatabase;
import com.example.club_system.repository.ClubDao;
import com.example.club_system.repository.StudentDao;
import com.example.club_system.repository.TeacherDatabaseDao;
import com.example.club_system.service.ifs.StudentService;
import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.ForgetPwdReq;
import com.example.club_system.vo.StudentGetClubDataReq;
import com.example.club_system.vo.StudentLoginReq;
import com.example.club_system.vo.StudentLoginRes;
import com.example.club_system.vo.StudentSearchRes;
import com.example.club_system.vo.StudentUpdataPwdReq;
import com.example.club_system.vo.StudentcreateOrUpdateReq;
import com.example.club_system.vo.StudentdeleteReq;
import com.example.club_system.vo.StudentsearchReq;

@Service
public class StudentServiceImpl implements StudentService {

	//信箱
	@Autowired
    private JavaMailSender javaMailSender; 
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private ClubDao clubDao;
	
	@Autowired
	private TeacherDatabaseDao teacherDatabaseDao;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Override
	public BasicRes updataPwd(StudentUpdataPwdReq req) {
		// 檢查參數
		if (!StringUtils.hasText(String.valueOf(req.getStudentId())) || !StringUtils.hasText(req.getOldPwd())//
				|| !StringUtils.hasText(req.getNewPwd())) {
			return new BasicRes(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getCode(),
					ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
		}

		// 確認帳號是否存在
		Optional<Student> op = studentDao.findById(req.getStudentId());
		// 確認帳號存在
		if (op.isEmpty()) {
			System.out.println(ResMessage.UPDATE_STUDENT_ID_NOT_FOUND.getMessage());
			return new BasicRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(),
					ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}

		// 取得最新的學生資料
		Student student = op.get();

		// 檢查舊密碼是否正確
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (!encoder.matches(req.getOldPwd(), student.getPwd())) {
			return new BasicRes(ResMessage.PSAAWORD_ERROR.getCode(), ResMessage.PSAAWORD_ERROR.getMessage());
		}

		// 更新密碼並保存
		student.setPwd(encoder.encode(req.getNewPwd()));
		studentDao.save(student);

		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	// 學生創建與更新
	@Override
	public BasicRes createOrUpdate(StudentcreateOrUpdateReq req) {
		if(req.getStudentId() > 0) {
			Optional<Student> studentId = studentDao.findById(req.getStudentId());
			if( !studentId.isPresent()) {
				return new BasicRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(), ResMessage.ACCOUNT_NOT_FOUND.getMessage());
			}
			Student studentData = studentId.get();
			studentData.setName(req.getName());
			studentData.setEmail(req.getEmail());
			studentDao.save(studentData);
			return new BasicRes(ResMessage.SUCCESS.getCode(),ResMessage.SUCCESS.getMessage());
		}
		if(req.getStudentId() == null) {
			return new BasicRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(), ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}
		studentDao.save(new Student(req.getStudentId(), req.getSemester(), encoder.encode(req.getPwd()), req.getGrade(), req.getName(), 
				req.getEmail(), req.getClubId(),
				req.getChoiceList(),req.getStatus())) ;
		return new BasicRes(ResMessage.SUCCESS.getCode(),ResMessage.SUCCESS.getMessage());
		
	}

	@Override
	public StudentSearchRes search(StudentsearchReq req) {
		String name = !StringUtils.hasText(req.getName()) ?"":req.getName();

		String status = !StringUtils.hasText(req.getStatus()) ?"":req.getStatus();

		String semester = !StringUtils.hasText(req.getSemester()) ?"":req.getSemester();
		
		String grade = !StringUtils.hasText(req.getGrade()) ?"":req.getGrade();
		
		Integer studentId = req.getStudentId();
		
		return new StudentSearchRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(),
				studentDao.findByNameAndStatusAndStudentIdAndSemesterAndGrade(name, status, studentId, semester, grade));

		
	}

	@Override
	public BasicRes delete(StudentdeleteReq req) {

		if (!CollectionUtils.isEmpty(req.getStudentIdList())) {
			// 刪除學生
			try {
				studentDao.deleteAllById(req.getStudentIdList());
			} catch (Exception e) {
				// 當 deleteAllById 方法中，id 的值不存在，JPA 會報錯
				// 因為在刪除之前， JPA會先搜帶入的 id 值，若沒結果就會報錯
				// 由於實際上也沒刪除任資料，因此就不需要對這個Exception 做處理
			}
		}

		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	@Override
	public StudentLoginRes login(StudentLoginReq req) {
		// 確認帳號是否已存在
		// 1. 因為會對收集出來的資料作密碼比對，所以要用 findById 來撈資料
		// 2. 只使用 findById 來撈資料的原因是存在資料庫中的 password 欄位的值是使用 BCryptPasswordEncoder
		// 加密後的值，其特性是一樣的內容值，每次加密後得到的結果都會不同，所以無法直接透過加密後的值來比對資料庫中的內容
		Optional<Student> getStudentId = studentDao.findById(req.getStudentId());
		// 確認帳號存在
		if (getStudentId.isEmpty()) {// op.isEmpty() 等同於 op.isEmpty() ==true，表示沒有資料
			return new StudentLoginRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(), ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}

		// 從 op 中取回 studentId 資訊
		Student studentIdData = getStudentId.get();

		// 判斷密碼是否正確
		if (!encoder.matches(req.getPwd(), studentIdData.getPwd())) { // 前面有驚嘆號 表示密碼比對失敗
			return new StudentLoginRes(ResMessage.PSAAWORD_ERROR.getCode(), ResMessage.PSAAWORD_ERROR.getMessage());
		}
		{
			return new StudentLoginRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(), studentIdData.getStudentId());
		}
	}

	

	@Override
	public StudentLoginRes studentGetClubData(StudentGetClubDataReq req) {
		// studentData: 取得學生Id的所有資訊
				Optional<Student> studentData = studentDao.findById(req.getStudentId());
				
				if(studentData.isEmpty()) {
					return new StudentLoginRes(ResMessage.STUDENT_ID_NOT_FOUND.getCode(), 
							ResMessage.STUDENT_ID_NOT_FOUND.getMessage());
				}
				
				// studentData:裡面有該學生的所有資訊
				Student student = studentData.get();
				
				// op: 用學生的的社團Id去撈該社團的所有資訊
				Optional<Club> op = clubDao.findById(student.getClubId());
				
				if(op.isEmpty()) {
					return new StudentLoginRes(ResMessage.PARAM_CLUB_ID_NOT_EXIST.getCode(), 
							ResMessage.PARAM_CLUB_ID_NOT_EXIST.getMessage());
				}
				
				// clubData: 拿到該社團Id的所有資訊
				Club clubData = op.get();
				
				Optional<TeacherDatabase> teacherData = teacherDatabaseDao.findById(clubData.getTeacherId());
				 
				  TeacherDatabase teacher = teacherData.get();
				
				return new StudentLoginRes(ResMessage.SUCCESS.getCode(), 
						ResMessage.SUCCESS.getMessage(),student.getClubId(), student.getName(),clubData.getName(),
						clubData.getClassroom(), clubData.getPay(), teacher.getName());
	}

	@Override
	public StudentLoginRes forgotPwd(ForgetPwdReq req) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(req.getStudentId() == null) {
			return new StudentLoginRes(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getCode(),ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
		}
		Optional<Student> studentId1 = studentDao.findBystudentId(req.getStudentId());
		if( !studentId1.isPresent()) {
			return new StudentLoginRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(),ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}
		Student studentData = studentId1.get();
		
		String verificationCodeString = RandomStringUtils.randomNumeric(6);
//        Integer verificationCode = Integer.parseInt(verificationCodeString);
        
        studentData.setPwd(encoder.encode(verificationCodeString));
		studentDao.save(studentData);
		sendVerificationEmail(studentData.getEmail(), verificationCodeString);
		return new StudentLoginRes(ResMessage.SUCCESS.getCode(),ResMessage.SUCCESS.getMessage());
	}

	private void sendVerificationEmail(String email, String verificationCodeString) {
		  SimpleMailMessage message = new SimpleMailMessage();
		     message.setTo(email);
		     message.setSubject("Verify Your Email Address");
		     message.setText("Your verification code is : " + verificationCodeString);
		     
		     try {
		         // Send email
		         javaMailSender.send(message);
		         System.out.println("Email sent successfully.");
		     } catch (MailException e) {
		         System.err.println("Failed to send email: " + e.getMessage());
		         // Handle exception appropriately
		     }
		
	}
	
	

	// 忘記密碼用Email認證
//	@Override
//	public BasicRes updataPwdByEmail(StudentForgotPwdByEmailReq req) {
//		// 檢查參數
//				if (!StringUtils.hasText(String.valueOf(req.getStudentId())) || !StringUtils.hasText(req.getEmail())//
//						|| !StringUtils.hasText(req.getNewPwd())) {
//					return new BasicRes(ResMessage.ACCOUNT_OR_EMAIL_ERROR.getCode(),
//							ResMessage.ACCOUNT_OR_EMAIL_ERROR.getMessage());
//				}
//
//				// 確認帳號是否存在
//				Optional<Student> op = studentDao.findById(req.getStudentId());
//				// 確認帳號存在
//				if (op.isEmpty()) {
//					return new BasicRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(),
//							ResMessage.ACCOUNT_NOT_FOUND.getMessage());
//				}
//
//				// 取得最新的學生資料
//				Student student = op.get();
//
//				// 檢查舊密碼是否正確
//				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//				if (StringUtils.hasText(req.getEmail())) {
//					return new BasicRes(ResMessage.PSAAWORD_ERROR.getCode(), ResMessage.PSAAWORD_ERROR.getMessage());
//				}
//
//				// 更新密碼並保存
//				student.setPwd(encoder.encode(req.getNewPwd()));
//				studentDao.save(student);
//
//				return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
//		
//		
//	}



	

}
