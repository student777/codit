use codit;

select * from test_case;
select * from problem_info;
select * from result;
select * from problem;
select * from source_code;



insert test_case value(null, "이현웅 잘생겼냐?", 2, "이현웅 잘생겼다", 1 );
insert problem value(null, 3, 4, "#TODO\n");
insert problem value(null, 2, 3, ' public class task{
	public static void main(String[] args){
		//TODO
	}
};
 	}
 }');

delete from result where applicant_id!=0;
delete from problem where language_id=1;
delete from source_code where problem_id=2 or problem_id=3 or problem_id=4;
delete from problem_info where id=4;
delete from source_code where id!=500;



select * from result where applicant_id=1 group by id ;
select * from applicant a, recruit r, cart c where a.recruit_id=r.id=c.recruit_id and a.id=1; 
SELECT id FROM test_case WHERE problem_info_id=1;
SELECT applicant_id as applicantId, test_case_id as testCaseId, correctness, used_memory as usedMemory, running_time as runningTime FROM result WHERE applicant_id=1 and test_case_id=1;