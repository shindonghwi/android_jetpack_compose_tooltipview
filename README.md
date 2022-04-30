# JetpackCompose_ToolTipView

<br/><br/>

## Preview
![ezgif com-gif-maker (1)](https://user-images.githubusercontent.com/45490440/166092724-0fdf3ddf-15e5-49fe-8c43-2d29d20b526c.jpg)

<br/><br/>

## Usage

```
# Sample
ToolTip(
    modifier: Modifier.padding(top = 6.dp),
    component = getToolTipComponent(
        style = ToolTipStyle.TITLE,
        direction = ToolTipDirection.CENTER_BOTTOM,
        backgroundColor = Color.Black,
        title = ToolTipText("I'm Title Text", Color.White, MaterialTheme.typography.subtitle1),
        content = ToolTipText("I'm Content Text", Color.White, MaterialTheme.typography.body2),
        icon = ToolTipIcon(img = R.drawable.ic_info, tint = Color.White) {
            Toast.makeText(this@MainActivity, "Icon Click", Toast.LENGTH_LONG).show()
        }
        cornerRoundValue = 20.dp
    )
)
```

```

Decription parameter

getToolTipComponent inner Params

  1. style => ToolTipStyle.Title or ToolTipStyle.Default // you want title is showing or hiding 
  
  2. direction => ToolTip Edge Direction!!
  
              ToolTipDirection.CENTER_TOP, // Tooltip Edge is Center Top
              ToolTipDirection.CENTER_BOTTOM,  // Tooltip Edge is Center Bottom
              ToolTipDirection.LEFT_BOTTOM, // Tooltip Edge is Left Bottom
              ToolTipDirection.LEFT_CENTER, // Tooltip Edge is Left Top
              ToolTipDirection.LEFT_TOP, // Tooltip Edge is Left Top
              ToolTipDirection.RIGHT_BOTTOM, // Tooltip Edge is Right Bottom
              ToolTipDirection.RIGHT_CENTER, // Tooltip Edge is Right Center
              ToolTipDirection.RIGHT_TOP // Tooltip Edge is Right Top

  3. backgroundColor => Tooltip Background Color
  
  4,5. title, content => TooltipText params ( text, textColor, textStyle )
  
  6. icon = ToolTipIcon params ( local Image Drawable, iconTint, iconClickEvent )
  
  7. cornerRoundValue = Tooltip Edge RoundedCorner Value
```


<br/><br/>

## License 
 ```code
Copyright 2022 ShinDongHwi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
