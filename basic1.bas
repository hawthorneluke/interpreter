;�������ăQ�[��
;�w�肳�ꂽ�͈͂̊Ԃɐ����𓖂ĂĂ݂�
;�����������烌�x���A�b�v�Ŕ͈͂��L���Ȃ�
;�������Ȃ����HP�������āA�[���ɂȂ�����Q�[���I�[�o�[
;�Ō�Ƀn�C�X�R�A�ɂ���Ẵ����L���O�L�^�E�\����

;�����ݒ�
cheats = false ;�����������Ă����i�f�o�O�ɕ֗��j
level = 1
score = 0
hpMax = 6
ended = false
rangeStep = 50
highscoreFile = "highscores.txt"
highscoreListMax = 10

;�ŏ��̃��x���̐ݒ�
hp = hpMax
range = level * rangeStep
rand = rand(range-1)+1
if cheats = true then print "Answer: " + rand
print "Level: " + toStr(level)

;������Ȃ�easter egg�̂��߁i�������t�ɏo�́j
perfect = 0
perfectThreshold = 3

;�Q�[�����[�v
do
	;�^�[���X�^�[�g
	print "HP: " + hp
	
	;���͂����߂�+�`�F�b�N
	do
		;���͂����߂�
		str = "1~" + toStr(range) + "�̐����𓖂ĂĂ݂Ă��������F "
		if perfect >= perfectThreshold then print -str else print str ;�A��perfect�ł�����Ȃ�easter egg(���̕��������ɂ���邱�Ƃŋt�ɂȂ�)
		guess = toInt(read())
		
		;���͂��`�F�b�N
		ok = false
		if guess > 0 then
			if guess <= range then
			ok = true endif
		endif
		
	loop until ok = true
	
	;�������Ă���
	if guess = rand then
		if hp = hpMax then perfect = perfect + 1 else perfect = 0 ;perfect����Ȃ��Ȃ�perfect�̃��Z�b�g
	
		print "�����I"
		
		;�X�R�A�Ǝ��̃��x���̐ݒ�
		score = score + (hp * range)
		print "Score :" + toStr(score) + "\n"
		
		level = level + 1
		print "Level: " + toStr(level)
		
		range = level * rangeStep
		
		hp = hpMax
		rand = rand(range-1)+1
		
		if cheats = true then print "Answer: " + rand
		
	;�������ĂȂ�������
	else
		hp = hp-1
		
		;����HP�Ȃ��Ȃ�
		if hp <= 0 then
		ended = true elseif guess > rand then
		;�傫���Ȃ�
		print "�傫������" else
		;�������Ȃ�
		print "����������" endif
	endif
	
;�Q�[�����[�v�̏I���
loop until ended = true

;���ʕ\��
print "Game Over"
print "Answer: " + rand
print "Score: " + toStr(score)


;�n�C�X�R�A
;�z�񂪂Ȃ��̂ŁAhashMap��function�Œ񋟂���Ă�mapPut, mapGet�𗘗p���čH�v���Ă�

;�ǂݍ���
if openFileRead(highscoreFile) = true then
	for i = 1 to highscoreListMax
		name = readFile()
		if name = null then name = " " ;�t�@�C�������������Ȃ�擾�����f�[�^��������f�[�^�^�ɕϊ�
		highscore = readFile()
		if highscore = null then highscore = 0 ;�t�@�C�������������Ȃ�擾�����f�[�^��������f�[�^�^�ɕϊ�
		mapPut("name" + i, name)
	mapPut("score" + i, highscore) next i
closeFileRead() else
	for i = 1 to highscoreListMax
		mapPut("name" + i, " ")
mapPut("score" + i, 0) next i endif

;�X�R�A����
done = false
for i = 1 to highscoreListMax
	if done = false then
		if score > mapGet("score" + i) then ;����̃X�R�A�������Ƒ傫���Ȃ�
			j = highscoreListMax
			while j > i ;����ȉ��̃X�R�A������ɂ��炷
				mapPut("name" + j, mapGet("name" + (j-1)))
				mapPut("score" + j, mapGet("score" + (j-1)))
			j = j-1 wend
			mapPut("score" + i, score) ;����̃X�R�A��}��
			print "�n�C�X�R�A�I�@���O����͂��Ă��������F"
			mapPut("name" + i, read())
		done = true endif
	endif
next i

;��������
if openFileWrite(highscoreFile) = true then
	for i = 1 to highscoreListMax
		name = mapGet("name" + i)
		highscore = mapGet("score" + i)
		writeFile(name)
	writeFile(highscore) next i
closeFileWrite() endif

;�X�R�A�\��
for i = 1 to highscoreListMax
	name = mapGet("name" + i)
	highscore = mapGet("score" + i)
print toStr(i) + "��:\t" + name + "\t" + toStr(highscore) + "�_" next i


END