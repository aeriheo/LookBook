/*global kakao*/
import React, { useEffect, useState } from 'react'
import { useLocation } from "react-router-dom";
import { libraryAPI } from '../../utils/axios'

const KakaoMaps = () => {
    const location = useLocation();
    const [bookIsbn, setBookIsbn] = useState(location.pathname.split("/")[2]);
    const [libGugun, setLibGugun] = useState("");

  useEffect(() => {

    const container = document.getElementById('map');
    const options = {
      center: new kakao.maps.LatLng(33.450701, 126.570667),
      level: 7
    };
    // 지도 생성
    let map = new kakao.maps.Map(container, options);
    // 주소 - 좌표 변환 객체 생성
    let geocoder = new kakao.maps.services.Geocoder();
    // 도서관 좌표 이미지 - 현재 코드로는 적용 불가 테스트용 이미지 Map Marker
    let imageLibraryMarker = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

    // Geolocation - Https 환경에서 위치 허용 필요
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(function (position) {
        let lat = position.coords.latitude;     // 위도
        let lon = position.coords.longitude;    // 경도
        let locPosition = new kakao.maps.LatLng(lat, lon);      // 위, 경도로 생성한 위치 정보

        // 위, 경도 정보로 주소 변환 함수
        searchDetailAddrFromCoords(locPosition, function (result, status) {
          if (status === kakao.maps.services.Status.OK) {
            // libGugun : 도서관 구 - (현재 위치의 구 이름 )
            setLibGugun(result[0].address.region_2depth_name);

            // MapMarker의 메세지를 나타내는 부분
            let message = '<div style="padding:5px;">' + libGugun + '</div>';

            // 현재 위치, message를 들고 Map에 Marker를 띄우는 부분.
            displayMarker(locPosition, message);
          }
        });

      })
    } else {
      let locPosition = new kakao.maps.LatLng(33.450701, 126.570667);
      let message = 'geolocation을 사용할수 없어요..';

      displayMarker(locPosition, message);
    }
    // 제대로 찍히면 지워도 됨.
    console.log(bookIsbn);
    console.log(libGugun);

    // 현재 위치 기반으로 bookIsbn에 해당하는 책을 소장하고 있는 도서관 리스트 호출 API
    const fetchData = async () => {
      const response = await libraryAPI.getLibrary(bookIsbn, libGugun);
      console.log(response);

      for (var i = 0; i < response.length; i++){
        let libraryLat = response[i].libLat;
        let libraryLong = response[i].libLong;
        let libraryName = response[i].libName;
        let libraryPosition = new kakao.maps.LatLng(libraryLat, libraryLong);

        console.log("["+i+"]Name: "+libraryName + " lat: "+libraryLat + " long: " + libraryLong);

        displaylibMarker(libraryPosition, libraryName);
      }
      // setData(response);
    };
    fetchData();

    // MapMarker 찍는 함수
    function displayMarker(locPosition, message) {
      let marker = new kakao.maps.Marker({
        map: map,
        position: locPosition
      });

      let iwContent = message;
      let iwRemoveable = true;

      let infowindow = new kakao.maps.InfoWindow({
        content: iwContent,
        removalble: iwRemoveable
      });

      infowindow.open(map, marker);
      map.setCenter(locPosition);
    }

    // 도서관 MapMarker 찍는 함수
    function displaylibMarker(locPosition, message) {
      let marker = new kakao.maps.Marker({
        map: map,
        position: locPosition,
      });

      let iwContent = message;
      let iwRemoveable = true;

      let infowindow = new kakao.maps.InfoWindow({
        content: iwContent,
        removalble: iwRemoveable
      });

      infowindow.open(map, marker);
    }

    // 현재 위치의 좌표로 주소를 반환하는 함수
    function searchDetailAddrFromCoords(coords, callback) {
      geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
    }
  }, [libGugun])  // 현재 위치에 따라서 요청해오는 위치가 바뀌므로 libGugun으로 react hook 설정


  return (
    <div>
      <div id="map" style={{ width: "100%", height: "500px" }}></div>
      <div>
        <span>아이고 사람 살려</span>
      </div>
    </div>

  )
}

export default KakaoMaps;