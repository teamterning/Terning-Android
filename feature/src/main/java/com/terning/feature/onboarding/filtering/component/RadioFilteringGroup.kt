//package com.terning.feature.onboarding.filtering.component
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.aspectRatio
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.items
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableIntStateOf
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.unit.dp
//import com.terning.core.R
//import com.terning.core.designsystem.component.button.RoundButton
//import com.terning.core.extension.noRippleClickable
//
//@Composable
//fun RadioFilteringGroup(){
//    val options = listOf(
//       RoundButton(
//           style = ,
//           paddingVertical = ,
//           cornerRadius = ,
//           text = ,
//           onButtonClick = { /*TODO*/ })
//    )
//
//    var selectedOption by rememberSaveable { mutableIntStateOf(options[0]) }
//
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(3),
//        verticalArrangement = Arrangement.spacedBy(20.dp),
//        horizontalArrangement = Arrangement.spacedBy(24.dp),
//        modifier = modifier
//            .padding(horizontal = 42.dp)
//    ) {
//        items(options) { option ->
//            Image(
//                painter = painterResource(
//                    id = if (option == selectedOption) R.drawable.ic_selected_character
//                    else option
//                ),
//                contentDescription = stringResource(id = R.string.sign_up_bottom_sheet_description),
//                modifier = modifier
//                    .aspectRatio(1f)
//                    .noRippleClickable {
//                        selectedOption = option
//                    }
//            )
//        }
//    }
//}