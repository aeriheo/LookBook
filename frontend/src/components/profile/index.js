import {useState} from 'react';
import AWS from 'aws-sdk';
import {userAPI} from '../../utils/axios';

const BUCKET_REGION = 'ap-northeast-2'
const ACCESS_KEY = 'AKIA5P6UZ4TDP7QXYAWF'
const SECRET_ACCESS_KEY = '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';
const S3_BUCKET = 'pjt2-lookbook';

AWS.config.update({
  region: BUCKET_REGION,
  accessKeyId: ACCESS_KEY,
  secretAccessKey: SECRET_ACCESS_KEY
});

function S3Upload() {
    const [selectedFile, setSelectedFile] = useState(null);
    async function loadfilename(){
        const result = await userAPI.userinfo();
        return result.data.userEmail.split('.')[0]+'_'+result.data.userEmail.split('.')[1]+`_profile.png`;
    }
    async function loadUserUrl (){
        const result = await userAPI.userinfo();
        return result.data.userProfileUrl;
    }
 

    const handleFileInput = (e) => {
        const file = e.target.files[0];
        const fileExt = file.name.split('.').pop();
        if(fileExt !=='jpg' && fileExt !== 'png' && fileExt !== 'JPG' && fileExt !== 'jpeg' && fileExt !== 'PNG' && fileExt !== 'JPEG'){
            alert('이미지 파일만 Upload 가능합니다.');
            return;
        }
        setSelectedFile(file);
    }

    const deleteProfile = (deleteKey) => new AWS.S3().deleteObject(
    {
        Bucket: S3_BUCKET,
        Key: deleteKey
    }
    )

    const uploadFile = (file) => {

        if(file==='' || file===null) alert('파일을 등록해주세요.');
    
        else{
            Promise.resolve(loadUserUrl()).then(function(value){
                if (value != null) {
                    const deleteKey = "profile/"+ value
                    
                    const promise_delete = deleteProfile(deleteKey).promise()

                    promise_delete.then(
                    function (data) {
                        // alert("이미지 삭제 성공")
                    },
                    function (err) {
                        return alert("이미지 삭제 실패", err.message)
                    }
                    )

                }
            })

            Promise.resolve(loadfilename()).then(function(filename){
                const uploadProfile = new AWS.S3.ManagedUpload({
                    params : {
                    ACL: 'public-read',
                    Body: file,
                    Key: "profile/" + filename,
                    Bucket: S3_BUCKET
                    },
                })
                const promise = uploadProfile.promise()

                promise.then(
                    function (data) {
                        alert("이미지 업로드 성공")
                        async function uploadUrl (filename){
                            await userAPI.changeProfile(filename);
                        }
                        uploadUrl(filename);
                        window.location.href='/mypage/mypage';
                    },
                    function (err) {
                    return alert("이미지 업로드 실패", err.message)
                    }
                )
            })
        }
    }

    return (
        <div>
            <input type="file" onChange={handleFileInput}/>
            <button onClick={() => uploadFile(selectedFile)}>프로필 사진 저장</button>
        </div>
    )
}

export default S3Upload;