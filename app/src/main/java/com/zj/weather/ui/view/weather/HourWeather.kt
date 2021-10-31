package com.zj.weather.ui.view.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qweather.sdk.bean.weather.WeatherHourlyBean
import com.zj.weather.utils.IconUtils
import com.zj.weather.utils.ImageLoader

@Composable
fun HourWeather(hourlyBeanList: List<WeatherHourlyBean.HourlyBean>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .alpha(0.9f),
        shape = RoundedCornerShape(10.dp)
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            items(hourlyBeanList) { hourlyBean ->
                HourWeatherItem(hourlyBean)
            }
        }
    }
}

@Composable
private fun HourWeatherItem(hourlyBean: WeatherHourlyBean.HourlyBean) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = hourlyBean.fxTime,
            fontSize = 15.sp,
            color = MaterialTheme.colors.primary
        )
        ImageLoader(
            data = IconUtils.getWeatherIcon(context, hourlyBean.icon),
            modifier = Modifier.padding(top = 7.dp)
        )
        Text(
            text = "${hourlyBean.temp}℃",
            modifier = Modifier.padding(top = 7.dp),
            fontSize = 15.sp,
            color = MaterialTheme.colors.primary
        )
    }
}

@Preview(showBackground = false, name = "小时item")
@Composable
fun HourWeatherItemPreview() {
    val hourlyBean = WeatherHourlyBean.HourlyBean()
    hourlyBean.fxTime = "21时"
    hourlyBean.temp = "15"
    hourlyBean.icon = "100"
    HourWeatherItem(hourlyBean = hourlyBean)
}