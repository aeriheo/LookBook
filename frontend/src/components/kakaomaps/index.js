/*global kakao*/
import React, { useEffect, useState } from 'react'
import { useLocation } from "react-router-dom";
import {useMediaQuery} from 'react-responsive';
import { libraryAPI } from '../../utils/axios'
import LB_Marker from '../../images/lb_marker.png';
import LocalPhoneRoundedIcon from '@mui/icons-material/LocalPhoneRounded';
import './style.css';

const KakaoMaps = () => {
    const isMobile = useMediaQuery({
        query: "(max-width : 768px)"
    });
    const location = useLocation();
    const [bookIsbn, setBookIsbn] = useState(location.pathname.split("/")[2]);
    const [libGugun, setLibGugun] = useState("");
    const [libList, setLibList] = useState([]);

  useEffect(() => {

    const container = document.getElementById('map');
    const options = {
      center: new kakao.maps.LatLng(37.5013068, 127.0385654),
      level: 7
    };
    // 지도 생성
    let map = new kakao.maps.Map(container, options);
    // 주소 - 좌표 변환 객체 생성
    let geocoder = new kakao.maps.services.Geocoder();
    // 도서관 좌표 이미지 - 현재 코드로는 적용 불가 테스트용 이미지 Map Marker
    const imageSrc = LB_Marker;
    const imageSize = new kakao.maps.Size(64,69);
    const imageOption = {offset:new kakao.maps.Point(27,69)};
    const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

    // Geolocation - Https 환경에서 위치 허용 필요
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(function (position) {
        let lat = position.coords.latitude;     // 위도
        let lon = position.coords.longitude;    // 경도
        let locPosition = new kakao.maps.LatLng(lat, lon);      // 위, 경도로 생성한 위치 정보
        map.panTo(locPosition);
        // 위, 경도 정보로 주소 변환 함수
        searchDetailAddrFromCoords(locPosition, function (result, status) {
          if (status === kakao.maps.services.Status.OK) {
            // libGugun : 도서관 구 - (현재 위치의 구 이름 )
            setLibGugun(result[0].address.region_2depth_name);

            // MapMarker의 메세지를 나타내는 부분
            let message =  '<div class="customoverlay">' +
                            '  <a>' +
                            '    <span class="title">'+libGugun+'</span>' +
                            '  </a>' +
                            '</div>';
            // 현재 위치, message를 들고 Map에 Marker를 띄우는 부분.
            displayMarker(locPosition, message);
          }
        });

      })
    } else {
      let locPosition = new kakao.maps.LatLng(37.5013068, 127.0385654);
      let message = 'geolocation을 사용할수 없어요..';

      displayMarker(locPosition, message);
    }

    // 현재 위치 기반으로 bookIsbn에 해당하는 책을 소장하고 있는 도서관 리스트 호출 API
    const fetchData = async () => {
      const response = await libraryAPI.getLibrary(bookIsbn, libGugun);
        setLibList(response);

      for (let i = 0; i < response.length; i++){
        let libraryLat = response[i].libLat;
        let libraryLong = response[i].libLong;
        let libraryName = '<span class="info-title">'+response[i].libName+'</span>';
        let content = '<div class="customoverlay">' +
                      `  <a href=${response[i].libUrl} target="_blank">` +
                      '    <span class="title">'+libraryName+'</span>' +
                      '  </a>' +
                      '</div>';
        let libraryPosition = new kakao.maps.LatLng(libraryLat, libraryLong);

        displaylibMarker(libraryPosition, content);
      }
    };
    fetchData();

    // MapMarker 찍는 함수
    function displayMarker(locPosition, message) {
      let marker = new kakao.maps.Marker({
        map: map,
        position: locPosition
      });
      let iwRemoveable = true;

      let customOverlay = new kakao.maps.CustomOverlay({
        map: map,
        position: locPosition,
        content: message,
        removalble: iwRemoveable
      });
      
    }

    // 도서관 MapMarker 찍는 함수
    function displaylibMarker(locPosition, message) {
      let marker = new kakao.maps.Marker({
        map: map,
        position: locPosition,
        image:markerImage
      });

      let customOverlay = new kakao.maps.CustomOverlay({
        map: map,
        position: locPosition,
        content: message,
        yAnchor: 1
      });

    }

    // 현재 위치의 좌표로 주소를 반환하는 함수
    function searchDetailAddrFromCoords(coords, callback) {
      geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
    }
  }, [libGugun])  // 현재 위치에 따라서 요청해오는 위치가 바뀌므로 libGugun으로 react hook 설정

  const libraryList = ()=>{
      let result = [];
      {libList&&libList.map(item=>{
          result = result.concat(
            <div>
                <div id='libListDivWeb'>
                    <div id='libListNameWeb'>{item.libName}</div>
                    <div id='libListAddrWeb'>{item.libAddr}</div>
                    <div id='libListTelWeb'><LocalPhoneRoundedIcon style={{marginRight:'5px'}}/> {item.libTel}</div>
                </div>
                <div id='libListDividerWeb'></div>
            </div>
          );
      })}
      if(result.length===0){
        result = result.concat(
            <div>
                <div id='libListDivWeb'>
                    <div id='libListNameWeb'>근처에 해당 책이 있는 도서관이 없습니다.</div>
                </div>
                <div id='libListDividerWeb'></div>
            </div>
        )
    }
      return result;
  }

  const libraryListMobile = ()=>{
    let result = [];
    {libList&&libList.map(item=>{
        result = result.concat(
          <div>
              <div id='libListDivMobile'>
                  <div id='libListNameMobile'>{item.libName}</div>
                  <div id='libListAddrMobile'>{item.libAddr}</div>
                  <div id='libListTelMobile'><LocalPhoneRoundedIcon style={{marginRight:'5px'}}/> {item.libTel}</div>
              </div>
              <div id='libListDividerMobile'></div>
          </div>
        );
    })}
    if(result.length===0){
        result = result.concat(
            <div>
                <div id='libListDivMobile'>
                    <div id='libListNameMobile'>근처에 해당 책이 있는 도서관이 없습니다.</div>
                </div>
                <div id='libListDividerMobile'></div>
            </div>
        )
    }
    return result;
}

  return (
    <div>
        {isMobile?(
            <div id='mobileDivForm'>
                <div id="map" className='mobileMap'></div>
                <div id='mobileListDiv'>
                    <div id='mobileListForm'>
                        <div style={{marginTop:'10px'}}>
                        {libraryListMobile()}
                        </div>
                    </div>
                </div>
            </div>
        ):(
            <div id='webDivForm'>
                <div id="map" className='webMap'></div>
                <div id='webListDiv'>
                    <div id='webListForm'>
                        <div style={{marginTop:'10px'}}>
                            {libraryList()}
                        </div>
                    </div>
                </div>
            </div>
        )}
    </div>

  )
}

export default KakaoMaps;